package com.example.weaveon.data.repoimpl

import android.content.Context
import android.util.Log
import com.example.weaveon.R
import com.example.weaveon.data.model.*
import com.example.weaveon.data.source.FirebaseService
import com.example.weaveon.data.source.GeminiService
import com.example.weaveon.domain.repository.ChatbotRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.HttpException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class ChatbotRepoImpl(private val context: Context) : ChatbotRepository {

    private val auth = FirebaseService.auth
    private val db = FirebaseService.db

    override suspend fun getChatbotReply(prompt: String): String {
        val apiKey = context.getString(R.string.gemini_api_key)
        val userId = auth.currentUser?.uid ?: throw IllegalStateException("User belum login")

        // Deteksi semua kategori dari prompt
        val matchedCategoryIds = detectCategories(prompt)

        // Ambil data anak dari Realtime DB
        val childData = getChildData(userId)

        // Bangun personalizationText berdasarkan banyak kategori
        val personalizationText = buildPersonalizationText(childData, matchedCategoryIds)

        // Instruksi agar Gemini santai & empatik
        val toneInstruction = "Tanggapi dengan santai dan penuh empati."

        // Gabungkan semua ke prompt final
        val finalPrompt = buildString {
            appendLine(toneInstruction)
            if (!personalizationText.isNullOrBlank()) {
                appendLine(personalizationText)
            }
            appendLine("Prompt user: $prompt")
        }

        val request = GeminiRequest(
            contents = listOf(Content(parts = listOf(Part(text = finalPrompt))))
        )

        Log.d("ChatbotRepoImpl", "Final prompt: $finalPrompt")

        return try {
            val response = GeminiService.service.sendPrompt(apiKey, request)
            response.candidates?.get(0)?.content?.parts?.get(0)?.text
                ?: throw IllegalStateException("Tidak ada respons valid dari API")
        } catch (e: HttpException) {
            when (e.code()) {
                424 -> throw Exception("HTTP 424: Gagal - Cek quota API atau format request")
                else -> throw Exception("HTTP ${e.code()}: ${e.message()}")
            }
        } catch (e: Exception) {
            throw Exception("Error: ${e.message ?: "Unknown error"}")
        }
    }

    private suspend fun getChildData(userId: String): ChildData? {
        val ref = db.getReference("users/$userId/children")

        return suspendCancellableCoroutine { cont ->
            ref.get().addOnSuccessListener { snapshot ->
                val firstChildSnapshot = snapshot.children.firstOrNull()
                if (firstChildSnapshot != null) {
                    val name = firstChildSnapshot.child("name").getValue(String::class.java) ?: ""
                    val age = firstChildSnapshot.child("age").getValue(String::class.java) ?: ""
                    val gender = firstChildSnapshot.child("gender").getValue(String::class.java) ?: ""

                    val personalizationSnapshot = firstChildSnapshot.child("personalization_data")
                    val personalizationData = mutableMapOf<String, List<String>>()

                    for (categorySnapshot in personalizationSnapshot.children) {
                        val key = categorySnapshot.key ?: continue
                        val answerList = mutableListOf<String>()
                        for (answerSnapshot in categorySnapshot.children) {
                            answerSnapshot.getValue(String::class.java)?.let { answerList.add(it) }
                        }
                        personalizationData[key] = answerList
                    }

                    val child = ChildData(
                        name = name,
                        age = age,
                        gender = gender,
                        personalization_data = personalizationData
                    )

                    cont.resume(child)
                } else {
                    cont.resume(null)
                }
            }.addOnFailureListener { exception ->
                cont.resumeWithException(exception)
            }
        }
    }

    /**
     * Mapping dari questionId (angka dalam String) ke categoryId (nama kategori string)
     */
    private fun questionIdToQuestionName(questionId: String): String? {
        // Cari FormCategory yang mengandung questionId ini dalam string yang dipisah "_"
        val formCategory = FormCategories.list.find {
            it.questionId.split("_").contains(questionId)
        } ?: return null

        val idList = formCategory.questionId.split("_")
        val questionList = formCategory.question.split("_")

        val index = idList.indexOf(questionId)
        return if (index in questionList.indices) questionList[index] else null
    }

    private fun buildPersonalizationText(child: ChildData?, categoryIds: List<String>): String? {
        if (child == null || categoryIds.isEmpty()) return null

        Log.d("ChatbotRepoImpl", "categoryIds received for personalization: $categoryIds")
        Log.d("ChatbotRepoImpl", "child.personalization_data keys: ${child.personalization_data.keys}")

        val builder = StringBuilder()
        builder.appendLine("Data anak: Nama: ${child.name}, usia: ${child.age}, jenis kelamin: ${child.gender}.")
        builder.appendLine("Personalisasi anak:")

        val processedCategoryIds = mutableSetOf<String>()

        for (categoryId in categoryIds) {
            Log.d("ChatbotRepoImpl", "Processing categoryId: $categoryId")

            val splitIds = categoryId.split("_")
            Log.d("ChatbotRepoImpl", "Split categoryIds: $splitIds")

            for (singleId in splitIds) {
                if (processedCategoryIds.contains(singleId)) {
                    Log.d("ChatbotRepoImpl", "Skipping duplicate singleId: $singleId")
                    continue
                }
                processedCategoryIds.add(singleId)

                val questionName = questionIdToQuestionName(singleId)
                Log.d("ChatbotRepoImpl", "Mapping singleId $singleId to questionName: $questionName")

                val answers = child.personalization_data[singleId]
                Log.d("ChatbotRepoImpl", "Answers for $singleId: $answers")

                if (!answers.isNullOrEmpty()) {
                    builder.appendLine("${questionName ?: singleId}: ${answers.joinToString(", ")}")
                } else {
                    Log.d("ChatbotRepoImpl", "No answers found for $singleId")
                }
            }
        }

        return builder.toString()
    }



    private fun detectCategories(prompt: String): List<String> {
        val text = prompt.lowercase()

        val matchedIds = mutableSetOf<String>()

        FormCategories.list.forEach { category ->
            val questionId = category.questionId
            val ids = questionId.split("_") // bisa lebih dari satu ID

            if (category.relatedKeywords.any { keyword -> text.contains(keyword) }) {
                matchedIds.addAll(ids)
            }
        }

        return matchedIds.toList()
    }
}

package com.example.weaveon.data.repoimpl

import android.content.Context
import android.util.Log
import com.example.weaveon.R
import com.example.weaveon.data.model.*
import com.example.weaveon.data.source.FirebaseService
import com.example.weaveon.data.source.GeminiService
import com.example.weaveon.domain.model.ChatMessageDomain
import com.example.weaveon.domain.repository.ChatbotRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import retrofit2.HttpException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import com.example.weaveon.data.mapper.ChatMessageMapper.toDomain

class ChatbotRepoImpl(private val context: Context) : ChatbotRepository {

    private val auth = FirebaseService.auth
    private val db = FirebaseService.db

    override suspend fun getChatbotReply(prompt: String): String {
        val apiKey = context.getString(R.string.gemini_api_key)
        val userId = auth.currentUser?.uid ?: throw IllegalStateException("User belum login")

        // Simpan prompt dari user terlebih dahulu
        saveChatMessage(userId, "user", prompt)

        val matchedCategoryIds = detectCategories(prompt)
        val childData = getChildData(userId)
        val personalizationText = buildPersonalizationText(childData, matchedCategoryIds)

        val toneInstruction = "Balas dengan nada santai dan empatik.\n"

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

        return try {
            val response = GeminiService.service.sendPrompt(apiKey, request)
            val reply = response.candidates?.get(0)?.content?.parts?.get(0)?.text
                ?: throw IllegalStateException("Tidak ada respons valid dari API")

            saveChatMessage(userId, "chatbot", reply)
            reply
        } catch (e: HttpException) {
            throw Exception("HTTP ${e.code()}: ${e.message()}")
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

    private fun saveChatMessage(userId: String, sender: String, message: String) {
        val ref = db.getReference("users/$userId/chats").push()
        val chatMessageData = ChatMessageData(
            sender = sender,
            message = message,
            timestamp = System.currentTimeMillis()
        )
        ref.setValue(chatMessageData)
            .addOnFailureListener { e -> Log.e("ChatbotRepoImpl", "Gagal simpan chat: ${e.message}") }
    }

    override suspend fun getSavedChats(): List<ChatMessageDomain> {
        val userId = auth.currentUser?.uid ?: throw IllegalStateException("User belum login")
        val ref = db.getReference("users/$userId/chats")

        return try {
            val snapshot = ref.get().await()
            val chatList = mutableListOf<ChatMessageData>()
            for (chatSnapshot in snapshot.children) {
                val chat = chatSnapshot.getValue(ChatMessageData::class.java)
                chat?.let { chatList.add(it) }
            }
            chatList.sortedBy { it.timestamp }.map { it.toDomain() }
        }

        catch (e: Exception) {
            Log.e("ChatbotRepoImpl", "Gagal mengambil chat: ${e.message}")
            emptyList()
        }
    }

    private fun questionIdToQuestionName(questionId: String): String? {
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

        val builder = StringBuilder()
        builder.appendLine("Data anak:\nNama: ${child.name}, usia: ${child.age}, jenis kelamin: ${child.gender}.\n")
        builder.appendLine("Personalisasi anak:")

        val processedCategoryIds = mutableSetOf<String>()

        for (categoryId in categoryIds) {
            val splitIds = categoryId.split("_")
            for (singleId in splitIds) {
                if (processedCategoryIds.contains(singleId)) continue
                processedCategoryIds.add(singleId)

                val questionName = questionIdToQuestionName(singleId)
                val answers = child.personalization_data[singleId]

                if (!answers.isNullOrEmpty()) {
                    builder.appendLine("${questionName ?: singleId}: ${answers.joinToString(", ")}")
                } else {
                    builder.appendLine("${questionName ?: singleId}: Tidak diketahui")
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
            val ids = questionId.split("_")
            if (category.relatedKeywords.any { keyword -> text.contains(keyword) }) {
                matchedIds.addAll(ids)
            }
        }

        return matchedIds.toList()
    }
}

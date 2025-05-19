package com.example.weaveon.data.repoimpl

import com.example.weaveon.data.model.ChildData
import com.example.weaveon.data.source.FirebaseService
import com.example.weaveon.domain.repository.KidscoverRepository
import kotlinx.coroutines.tasks.await

class KidscoverRepoImpl : KidscoverRepository {

    private val auth = FirebaseService.auth
    private val db = FirebaseService.db

    override suspend fun saveChildData(
        childName: String,
        age: String,
        gender: String,
        answers: Map<Int, List<String>>,
        onResult: (Boolean, String?) -> Unit
    ) {
        try {
            val userId = auth.currentUser?.uid
            if (userId == null) {
                onResult(false, "User not authenticated")
                return
            }

            // Konversi Int key jadi String dan sanitasi key-nya
            val sanitizedAnswers = answers.mapKeys { sanitizeKey(it.key.toString()) }

            val childData = ChildData(
                name = childName,
                age = age,
                gender = gender,
                personalization_data = sanitizedAnswers
            )

            db.getReference("users")
                .child(userId)
                .child("children")
                .push()
                .setValue(childData)
                .await()

            onResult(true, null)
        } catch (e: Exception) {
            onResult(false, e.message)
        }
    }

    private fun sanitizeKey(key: String): String {
        return key.replace("[.]".toRegex(), "")
    }
}

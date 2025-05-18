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
        answers: Map<String, List<String>>,
        onResult: (Boolean, String?) -> Unit
    ) {
        val sanitizedAnswers = answers.mapKeys { (key, _) -> sanitizeKey(key) }

        try {
            val userId = auth.currentUser?.uid
            if (userId == null) {
                onResult(false, "User not authenticated")
                return
            }

            val childData = ChildData(
                name = childName,
                age = age,
                gender = gender,
                answers = sanitizedAnswers
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
        return key.replace("[./#$\\[\\]]".toRegex(), "_")
    }
}

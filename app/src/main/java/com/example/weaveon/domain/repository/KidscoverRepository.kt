package com.example.weaveon.domain.repository

interface KidscoverRepository {
    suspend fun saveChildData(
        childName: String,
        age: String,
        gender: String,
        answers: Map<String, List<String>>,
        onResult: (Boolean, String?) -> Unit)
}
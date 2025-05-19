package com.example.weaveon.domain.repository

interface KidscoverRepository {
    suspend fun saveChildData(
        childName: String,
        age: String,
        gender: String,
        answers: Map<Int, List<String>>,
        onResult: (Boolean, String?) -> Unit)
}
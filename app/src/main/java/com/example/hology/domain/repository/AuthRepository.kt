package com.example.hology.domain.repository

interface AuthRepository {
    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit)
}
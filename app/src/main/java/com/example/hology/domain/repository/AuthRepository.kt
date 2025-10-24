package com.example.hology.domain.repository

interface AuthRepository {
    suspend fun login(email: String, password: String): String
    fun register(name: String, email: String, password: String, passwordConfirmation: String, onResult: (Boolean, String?) -> Unit)
    suspend fun signInWithGoogle(idToken: String): String
    fun forgotPassword(email: String, onResult: (Boolean, String?) -> Unit)
}
package com.example.hology.domain.repository

interface AuthRepository {
    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit)
    fun register(name: String, email: String, password: String, passwordConfirmation: String, onResult: (Boolean, String?) -> Unit)
    fun forgotPassword(email: String, onResult: (Boolean, String?) -> Unit)

}
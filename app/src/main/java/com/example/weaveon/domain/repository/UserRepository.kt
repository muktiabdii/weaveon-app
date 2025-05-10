package com.example.weaveon.domain.repository

interface UserRepository {
    fun register(name: String, email: String, password: String, passwordKonfirmation: String, onResult: (Boolean, String?) -> Unit)
    fun login (email: String, password: String, onResult: (Boolean, String?) -> Unit)
}
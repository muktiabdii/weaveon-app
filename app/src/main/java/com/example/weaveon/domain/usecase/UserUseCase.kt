package com.example.weaveon.domain.usecase

import com.example.weaveon.domain.repository.UserRepository

class UserUseCase(private val userRepository: UserRepository) {
    fun register(name: String, email: String, password: String, passwordKonfirmation: String, onResult: (Boolean, String?) -> Unit) {
        userRepository.register(name, email, password, passwordKonfirmation, onResult)
    }
}
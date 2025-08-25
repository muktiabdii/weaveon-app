package com.example.hology.domain.usecase

import com.example.hology.domain.model.User
import com.example.hology.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserUseCase(private val userRepository: UserRepository) {

    // function untuk mendapatkan user dari remote
    suspend fun getUserFromRemote(uid: String): User? {
        return userRepository.getUserFromRemote(uid)
    }

    // function untuk mendapatkan user dari cache
    suspend fun saveUserToCache(uid: String, name: String, email: String) {
        userRepository.saveUserToCache(uid, name, email)
    }

    // function untuk mendapatkan user uid dari cache
    fun getUserUidFlow(): Flow<String?> {
        return userRepository.getUserUidFlow()
    }
}
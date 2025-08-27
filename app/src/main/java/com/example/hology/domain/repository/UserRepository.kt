package com.example.hology.domain.repository

import com.example.hology.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUserFromRemote(uid: String): User?
    suspend fun saveUserToCache(uid: String, name: String, email: String)
    fun getUserUidFlow(): Flow<String?>
    suspend fun clearUser()
    suspend fun editProfile(uid: String, name: String, email: String): Boolean
}
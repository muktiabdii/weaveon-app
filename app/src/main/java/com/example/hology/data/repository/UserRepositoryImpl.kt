package com.example.hology.data.repository

import com.example.hology.cache.UserData
import com.example.hology.data.datastore.PreferencesManager
import com.example.hology.di.FirebaseProvider
import com.example.hology.domain.model.User
import com.example.hology.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(private val preferencesManager: PreferencesManager) : UserRepository {

    private val database = FirebaseProvider.database

    // function untuk mendapatkan user dari remote
    override suspend fun getUserFromRemote(uid: String): User? {
        val snapshot = database.child("users").child(uid).get().await()
        return snapshot.getValue(User::class.java)
    }

    // function untuk mendapatkan user dari cache
    override suspend fun saveUserToCache(uid: String, name: String, email: String) {
        preferencesManager.saveUser(uid, name, email)
        UserData.set(uid, name, email)
    }

    // function untuk mendapatkan user uid dari cache
    override fun getUserUidFlow(): Flow<String?> {
        return preferencesManager.userUid
    }

    // function untuk menghapus user dari cache
    override suspend fun clearUser() {
        preferencesManager.clearUser()
        UserData.clear()
    }
}
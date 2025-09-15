package com.example.hology.data.repository

import android.content.Context
import com.example.hology.cache.UserData
import com.example.hology.data.datastore.ExercisePreferencesManager
import com.example.hology.data.datastore.UserPreferencesManager
import com.example.hology.data.datastore.WevyPreferencesManager
import com.example.hology.data.remote.firebase.FirebaseProvider
import com.example.hology.domain.model.User
import com.example.hology.domain.repository.UserRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import com.example.hology.R

class UserRepositoryImpl(
    private val userPreferencesManager: UserPreferencesManager,
    private val exercisePreferencesManager: ExercisePreferencesManager,
    private val wevyPreferencesManager: WevyPreferencesManager,
    private val context: Context
) : UserRepository {

    private val database = FirebaseProvider.database
    private val auth = FirebaseProvider.auth

    // function untuk mendapatkan user dari remote
    override suspend fun getUserFromRemote(uid: String): User? {
        val snapshot = database.child("users").child(uid).get().await()
        return snapshot.getValue(User::class.java)
    }

    // function untuk mendapatkan user dari cache
    override suspend fun saveUserToCache(uid: String, name: String, email: String) {
        userPreferencesManager.saveUser(uid, name, email)
        UserData.set(uid, name, email)
    }

    // function untuk mendapatkan user uid dari cache
    override fun getUserUidFlow(): Flow<String?> {
        return userPreferencesManager.userUid
    }

    // function untuk menghapus user dari cache
    override suspend fun logout() {
        exercisePreferencesManager.clear()
        wevyPreferencesManager.clear()
        userPreferencesManager.clear()
        UserData.clear()

        FirebaseProvider.auth.signOut()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        googleSignInClient.signOut()
    }

    // function untuk edit profile
    override suspend fun editProfile(uid: String, name: String, email: String): Boolean {
        try {

            // update ke firebase
            val userUpdates = hashMapOf<String, Any>(
                "uid" to uid,
                "name" to name,
                "email" to email
            )
            database.child("users").child(uid).updateChildren(userUpdates).await()

            // update di datastore
            userPreferencesManager.saveUser(uid, name, email)

            // update di cache
            UserData.set(uid, name, email)
            return true
        }

        catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    // function untuk hapus akun
    override suspend fun deleteAccount(uid: String) {
        try {
            // hapus user dari firebase
            auth.currentUser?.delete()?.await()
            database.child("users").child(uid).removeValue().await()

            // hapus user dari datastore
            exercisePreferencesManager.clear()
            wevyPreferencesManager.clear()
            userPreferencesManager.clear()

            // hapus user dari cache
            UserData.clear()
        }
        catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}
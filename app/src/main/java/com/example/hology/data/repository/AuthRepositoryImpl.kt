package com.example.hology.data.repository

import com.example.hology.di.FirebaseProvider
import com.example.hology.domain.repository.AuthRepository

class AuthRepositoryImpl: AuthRepository {

    // inisiasi firebase auth dan database
    private val auth = FirebaseProvider.auth
    private val database = FirebaseProvider.database

    // function login
    override fun login(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                }

                // jika login gagal, kirim pesan error
                else {
                    val errorMessage = getLocalizedErrorMessage(task.exception?.message)
                    onResult(false, errorMessage)
                }
            }
    }

    // function untuk mendapatkan pesan error yang sesuai
    private fun getLocalizedErrorMessage(errorMessage: String?): String {
        return when {
            errorMessage?.contains("The email address is badly formatted") == true ->
                "Format email salah"

            errorMessage?.contains("The supplied auth credential is incorrect, malformed or has expired.") == true ->
                "Silahkan periksa kembali email dan password Anda"

            errorMessage?.contains("A network error") == true ->
                "Terjadi kesalahan jaringan"

            errorMessage?.contains("The email address is already in use by another account") == true ->
                "Email sudah terdaftar"

            else -> errorMessage ?: "Terjadi kesalahan saat login"
        }
    }
}
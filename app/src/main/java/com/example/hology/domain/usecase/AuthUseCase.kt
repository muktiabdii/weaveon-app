package com.example.hology.domain.usecase

import com.example.hology.domain.repository.AuthRepository

class AuthUseCase(private val authRepository: AuthRepository) {

    // function login
    suspend fun login(email: String, password: String): Result<String> {
        val validateLogin = validateLogin(email, password)
        if (validateLogin != null) {
            return Result.failure(Exception(validateLogin))
        }

        return try {
            val uid = authRepository.login(email.trim(), password.trim())
            Result.success(uid)
        }

        catch (e: Exception) {
            Result.failure(e)
        }
    }

    // function register
    fun register(name: String, email: String, password: String, passwordConfirmation: String, onResult: (Boolean, String?) -> Unit) {
        val validateRegister = validateRegister(name, email, password, passwordConfirmation)
        if (validateRegister != null){
            onResult(false, validateRegister)
            return
        }
        authRepository.register(name.trim(), email.trim(), password.trim(), passwordConfirmation.trim(), onResult)
    }

    // function sign in with google
    suspend fun signInWithGoogle(idToken: String): Result<String> {
        return runCatching { authRepository.signInWithGoogle(idToken) }
    }

    // function forgot password
    fun forgotPassword(email: String, onResult: (Boolean, String?) -> Unit) {
        val validateForgotPassword = validateForgotPassword(email)
        if (validateForgotPassword != null){
            onResult(false, validateForgotPassword)
            return
        }
        authRepository.forgotPassword(email.trim(), onResult)
    }

    // function validasi login
    private fun validateLogin(email: String, password: String): String? {
        if (email.trim().isEmpty() || password.trim().isEmpty()) {
            return "Email dan password harus diisi"
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Format email salah"
        }

        if (password.length < 8) {
            return "Password harus lebih dari 8 karakter"
        }
        return null
    }

    // function validasi register
    private fun validateRegister(name: String, email: String, password: String, passwordConfirmation: String): String? {
        if (name.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty() || passwordConfirmation.trim().isEmpty()) {
            return "Semua field harus diisi"
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Format email salah"
        }

        if (password.length < 8) {
            return "Password harus lebih dari 8 karakter"
        }

        if (password != passwordConfirmation) {
            return "Konfirmasi password tidak sesuai"
        }
        return null
    }

    // function validasi forgot password
    private fun validateForgotPassword(email: String): String? {
        if (email.trim().isEmpty()) {
            return "Email harus diisi"
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Format email salah"
        }
        return null
    }
}
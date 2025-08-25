package com.example.hology.domain.usecase

import com.example.hology.domain.repository.AuthRepository

class AuthUseCase(private val authRepository: AuthRepository) {

    // function login
    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        val validateLogin = validateLogin(email, password)
        if (validateLogin != null){
            onResult(false, validateLogin)
            return
        }
        authRepository.login(email.trim(), password.trim(), onResult)
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
}
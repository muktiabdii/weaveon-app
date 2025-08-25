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

    // function register
    fun register(name: String, email: String, password: String, passwordConfirmation: String, onResult: (Boolean, String?) -> Unit) {
        val validateRegister = validateRegister(name, email, password, passwordConfirmation)
        if (validateRegister != null){
            onResult(false, validateRegister)
            return
        }
        authRepository.register(name.trim(), email.trim(), password.trim(), passwordConfirmation.trim(), onResult)
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
}
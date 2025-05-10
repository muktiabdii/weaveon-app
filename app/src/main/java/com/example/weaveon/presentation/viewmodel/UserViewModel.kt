package com.example.weaveon.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weaveon.domain.usecase.UserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Patterns
import androidx.lifecycle.ViewModelProvider

class UserViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    // state data input
    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _passwordKonfirmation = MutableStateFlow("")
    val passwordKonfirmation: StateFlow<String> = _passwordKonfirmation

    // state umum
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // setter input
    fun setName(value: String) { _name.value = value }
    fun setEmail(value: String) { _email.value = value }
    fun setPassword(value: String) { _password.value = value }
    fun setPasswordKonfirmation(value: String) { _passwordKonfirmation.value = value }

    // reset input
    fun clearAllFields() {
        _name.value = ""
        _email.value = ""
        _password.value = ""
        _passwordKonfirmation.value = ""
        _error.value = null
    }

    fun clearError() { _error.value = null }

    // Validasi input khusus register
    private fun validateRegisterInput(): Boolean {
        if (name.value.isBlank() || email.value.isBlank() || password.value.isBlank() || passwordKonfirmation.value.isBlank()) {
            _error.value = "Semua field harus diisi"
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            _error.value = "Email tidak valid"
            return false
        }

        if (password.value.length < 6) {
            _error.value = "Password minimal 6 karakter"
            return false
        }

        if (password.value != passwordKonfirmation.value) {
            _error.value = "Konfirmasi password tidak cocok"
            return false
        }

        _error.value = null
        return true
    }

    // Validasi input khusus login
    private fun validateLoginInput(): Boolean {
        if (email.value.isBlank() || password.value.isBlank()) {
            _error.value = "Email dan password harus diisi"
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            _error.value = "Email tidak valid"
            return false
        }

        _error.value = null
        return true
    }


    fun register(onResult: (Boolean) -> Unit) {
        if (!validateRegisterInput()) {
            onResult(false)
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            userUseCase.register(
                name = name.value,
                email = email.value,
                password = password.value,
                passwordKonfirmation = passwordKonfirmation.value
            ) { success, message ->
                if (success) {
                    clearAllFields()
                }

                else {
                    _error.value = message
                }

                _isLoading.value = false
                onResult(success)
            }
        }
    }

    fun login(onResult: (Boolean) -> Unit) {
        if (!validateLoginInput()) {
            onResult(false)
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            userUseCase.login(
                email = email.value,
                password = password.value
            ) { success, message ->
                if (success) {
                    clearAllFields()
                }

                else {
                    _error.value = message
                }

                _isLoading.value = false
                onResult(success)
            }
        }
    }

    class Factory(private val userUseCase: UserUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UserViewModel(userUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

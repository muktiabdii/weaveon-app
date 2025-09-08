package com.example.hology.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hology.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import com.example.hology.domain.usecase.UserUseCase
import kotlinx.coroutines.launch

// general state
sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel(
    private val authUseCase: AuthUseCase,
    private val userUseCase: UserUseCase
): ViewModel() {
    // login state
    private val _loginState = MutableStateFlow<AuthState>(AuthState.Idle)
    val loginState: StateFlow<AuthState> = _loginState

    // register state
    private val _registerState = MutableStateFlow<AuthState>(AuthState.Idle)
    val registerState: StateFlow<AuthState> = _registerState

    // forgot password state
    private val _forgotPasswordState = MutableStateFlow<AuthState>(AuthState.Idle)
    val forgotPasswordState: StateFlow<AuthState> = _forgotPasswordState

    // reset state
    fun resetLoginState() {
        _loginState.value = AuthState.Idle
    }

    fun resetRegisterState() {
        _registerState.value = AuthState.Idle
    }

    fun resetForgotPasswordState() {
        _forgotPasswordState.value = AuthState.Idle
    }

    // function login
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = AuthState.Loading
            val result = authUseCase.login(email, password)
            result.onSuccess { uid ->
                if (uid.isNotEmpty()) {
                    loadUser(uid)
                }

                _loginState.value = AuthState.Success
            }.onFailure { e ->
                _loginState.value = AuthState.Error(e.message ?: "Login gagal")
            }
        }
    }

    // function load user
    suspend fun loadUser(uid: String) {
        val user = userUseCase.getUserFromRemote(uid)
        if (user != null) {
            userUseCase.saveUserToCache(user.uid, user.name, user.email)
        }
    }

    // function register
    fun register(
        name: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ) {
        viewModelScope.launch {
            _registerState.value = AuthState.Loading
            authUseCase.register(name, email, password, passwordConfirmation) { success, message ->
                _registerState.value = if (success) AuthState.Success else AuthState.Error(message ?: "Register gagal")
            }
        }
    }

    // function forgot password
    fun forgotPassword(
        email: String
    ) {
        viewModelScope.launch {
            _forgotPasswordState.value = AuthState.Loading
            authUseCase.forgotPassword(email) { success, message ->
                _forgotPasswordState.value = if (success) AuthState.Success else AuthState.Error(message ?: "Forgot password gagal")
            }
        }
    }

    class Factory(
        private val authUseCase: AuthUseCase,
        private val userUseCase: UserUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AuthViewModel(authUseCase, userUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
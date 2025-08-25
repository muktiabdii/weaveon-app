package com.example.hology.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hology.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// general state
sealed class State {
    object Idle : State()
    object Loading : State()
    object Success : State()
    data class Error(val message: String) : State()
}

class AuthViewModel(private val authUseCase: AuthUseCase): ViewModel() {
    // login state
    private val _loginState = MutableStateFlow<State>(State.Idle)
    val loginState: StateFlow<State> = _loginState

    // register state
    private val _registerState = MutableStateFlow<State>(State.Idle)
    val registerState: StateFlow<State> = _registerState

    // forgot password state
    private val _forgotPasswordState = MutableStateFlow<State>(State.Idle)
    val forgotPasswordState: StateFlow<State> = _forgotPasswordState

    // reset state
    fun resetLoginState() {
        _loginState.value = State.Idle
    }

    fun resetRegisterState() {
        _registerState.value = State.Idle
    }

    fun resetForgotPasswordState() {
        _forgotPasswordState.value = State.Idle
    }

    // function login
    fun login(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _loginState.value = State.Loading
            authUseCase.login(email, password) { success, message ->
                _loginState.value = if (success) State.Success else State.Error(message ?: "Login gagal")
            }
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
            _registerState.value = State.Loading
            authUseCase.register(name, email, password, passwordConfirmation) { success, message ->
                _registerState.value = if (success) State.Success else State.Error(message ?: "Register gagal")
            }
        }
    }

    // function forgot password
    fun forgotPassword(
        email: String
    ) {
        viewModelScope.launch {
            _forgotPasswordState.value = State.Loading
            authUseCase.forgotPassword(email) { success, message ->
                _forgotPasswordState.value = if (success) State.Success else State.Error(message ?: "Forgot password gagal")
            }
        }
    }

    class Factory(private val authUseCase: AuthUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AuthViewModel(authUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
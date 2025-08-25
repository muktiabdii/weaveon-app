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

    // reset state
    fun resetLoginState() {
        _loginState.value = State.Idle
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
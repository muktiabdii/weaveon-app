package com.example.hology.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.hology.cache.UserData
import com.example.hology.domain.usecase.UserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// general state
data class State(
    val uid: String = "",
    val name: String = "",
    val email: String = ""
)

class UserViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    // user state
    private val _userState = MutableStateFlow(State())
    val userState: StateFlow<State> = _userState

    // function set user state
    fun setName(name: String) {
        _userState.value = _userState.value.copy(name = name)
    }

    fun setEmail(email: String) {
        _userState.value = _userState.value.copy(email = email)
    }

    // launch loadUserData saat ViewModel dibuat
    init {
        loadUserData()
    }

    // function load user data
    fun loadUserData() {
        viewModelScope.launch {
            _userState.value = State(
                uid = UserData.uid,
                name = UserData.name,
                email = UserData.email
            )
        }
    }

    // function refresh user data
    fun refreshUserData() {
        loadUserData()
    }

    // function untuk edit profile
    fun editProfile(name: String, email: String) {
        viewModelScope.launch {
            try {
                val uid = _userState.value.uid
                val result = userUseCase.editProfile(uid, name, email)
                if (result) {
                    _userState.value = _userState.value.copy(
                        name = name,
                        email = email
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // function logout
    fun logout() {
        viewModelScope.launch {
            userUseCase.logout()
        }
    }

    fun deleteAccount() {
        viewModelScope.launch {
            try {
                val uid = _userState.value.uid
                userUseCase.deleteAccount(uid)
            } catch (e: Exception) {
                e.printStackTrace()
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
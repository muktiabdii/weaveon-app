package com.example.weaveon.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weaveon.domain.usecase.ChatbotUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatbotViewModel(
    private val chatbotUseCase: ChatbotUseCase
) : ViewModel() {

    private val _reply = MutableStateFlow<String?>(null) // Ubah ke String? untuk mendukung null
    val reply: StateFlow<String?> = _reply

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun sendPrompt(prompt: String) {
        if (prompt.isEmpty()) return // Hindari pemanggilan dengan prompt kosong
        viewModelScope.launch {
            _isLoading.value = true
            _reply.value = null // Reset reply sebelum permintaan baru
            _error.value = null // Reset error sebelum permintaan baru
            try {
                val result = chatbotUseCase(prompt)
                _reply.value = result
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    class ChatbotViewModelFactory(
        private val chatbotUseCase: ChatbotUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ChatbotViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ChatbotViewModel(chatbotUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
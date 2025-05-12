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

    private val _reply = MutableStateFlow("")
    val reply: StateFlow<String> = _reply

    fun sendPrompt(prompt: String) {
        viewModelScope.launch {
            val result = chatbotUseCase(prompt)
            _reply.value = result
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

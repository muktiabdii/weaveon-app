package com.example.weaveon.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weaveon.domain.model.ChatMessageDomain
import com.example.weaveon.domain.usecase.ChatbotUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatbotViewModel(
    private val chatbotUseCase: ChatbotUseCase
) : ViewModel() {

    private val _reply = MutableStateFlow<String?>(null)
    val reply: StateFlow<String?> = _reply

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _chatHistory = MutableStateFlow<List<ChatMessageDomain>>(emptyList())
    val chatHistory: StateFlow<List<ChatMessageDomain>> = _chatHistory

    fun sendPrompt(prompt: String) {
        if (prompt.isEmpty()) return
        viewModelScope.launch {
            _isLoading.value = true
            _reply.value = null
            _error.value = null
            try {
                // Tambah pesan user ke chat history lokal agar bubble langsung muncul
                _chatHistory.update { currentList ->
                    currentList + ChatMessageDomain(content = prompt, isOutgoing = true, isLoading = false)
                }

                // Kirim prompt ke use case dan tunggu balasan
                val result = chatbotUseCase(prompt)
                _reply.value = result

                // Tambah balasan Gemini ke chat history agar bubble muncul juga
                if (result != null) {
                    _chatHistory.update { currentList ->
                        currentList + ChatMessageDomain(content = result, isOutgoing = false, isLoading = false)
                    }
                }

                // Reload chat dari storage/database untuk sinkronisasi data
                loadSavedChats()
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadSavedChats() {
        viewModelScope.launch {
            try {
                val chats = chatbotUseCase.getSavedChats()
                _chatHistory.value = chats
            } catch (e: Exception) {
                _error.value = e.message ?: "Gagal memuat riwayat chat"
            }
        }
    }

    class ChatbotViewModelFactory(private val chatbotUseCase: ChatbotUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ChatbotViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ChatbotViewModel(chatbotUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

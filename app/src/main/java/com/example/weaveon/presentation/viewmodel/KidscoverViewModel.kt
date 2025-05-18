package com.example.weaveon.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weaveon.domain.usecase.KidscoverUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class KidscoverViewModel(private val kidscoverUseCase: KidscoverUseCase) : ViewModel() {

    // State input anak
    private val _childName = MutableStateFlow("")
    val childName: StateFlow<String> = _childName

    private val _age = MutableStateFlow("")
    val age: StateFlow<String> = _age

    private val _gender = MutableStateFlow("")
    val gender: StateFlow<String> = _gender

    private val _answers = MutableStateFlow<Map<String, List<String>>>(emptyMap())
    val answers: StateFlow<Map<String, List<String>>> = _answers

    // State umum
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Setter input
    fun setChildName(value: String) { _childName.value = value }
    fun setAge(value: String) { _age.value = value }
    fun setGender(value: String) { _gender.value = value }
    fun setAnswers(value: Map<String, List<String>>) { _answers.value = value }

    // Reset semua input dan error
    fun clearAllFields() {
        _childName.value = ""
        _age.value = ""
        _gender.value = ""
        _answers.value = emptyMap()
        _error.value = null
    }

    fun clearError() { _error.value = null }

    // Validasi input sederhana
    private fun validateInput(): Boolean {
        if (childName.value.isBlank() || age.value.isBlank() || gender.value.isBlank()) {
            _error.value = "Nama anak, umur, dan jenis kelamin harus diisi"
            return false
        }

        _error.value = null
        return true
    }

    // Fungsi simpan data anak
    fun saveChildData(onResult: (Boolean) -> Unit) {
        if (!validateInput()) {
            onResult(false)
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            kidscoverUseCase(
                childName = childName.value,
                age = age.value,
                gender = gender.value,
                answers = answers.value
            ) { success, message ->
                if (success) {
                    clearAllFields()
                } else {
                    _error.value = message
                }
                _isLoading.value = false
                onResult(success)
            }
        }
    }

    // Factory untuk ViewModel
    class Factory(private val kidscoverUseCase: KidscoverUseCase) : androidx.lifecycle.ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(KidscoverViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return KidscoverViewModel(kidscoverUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

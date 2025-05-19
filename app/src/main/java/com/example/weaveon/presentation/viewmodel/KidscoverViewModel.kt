package com.example.weaveon.presentation.viewmodel

import android.util.Log
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

    private val _answers = MutableStateFlow<Map<Int, List<String>>>(emptyMap())
    val answers: StateFlow<Map<Int, List<String>>> = _answers

    // State umum
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Setter input
    fun setChildName(value: String) { _childName.value = value }
    fun setAge(value: String) { _age.value = value }
    fun setGender(value: String) { _gender.value = value }
    fun setAnswers(value: Map<Int, List<String>>) { _answers.value = value }

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
        Log.d("saveChildData", "Masuk ke saveChildData()")
        Log.d("saveChildData", "Nama Anak: ${childName.value}")
        Log.d("saveChildData", "Usia Anak: ${age.value}")
        Log.d("saveChildData", "Gender Anak: ${gender.value}")
        Log.d("saveChildData", "Jawaban: ${answers.value}")

        if (!validateInput()) {
            Log.d("saveChildData", "Validasi gagal")
            onResult(false)
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            Log.d("saveChildData", "Memulai proses penyimpanan...")

            kidscoverUseCase(
                childName = childName.value,
                age = age.value,
                gender = gender.value,
                answers = answers.value
            ) { success, message ->
                Log.d("saveChildData", "Hasil penyimpanan: success=$success, message=$message")
                if (success) {
                    Log.d("saveChildData", "Clear semua field karena success")
                    clearAllFields()
                } else {
                    Log.d("saveChildData", "Gagal simpan, error: $message")
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

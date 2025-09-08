package com.example.hology.ui.exercise

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.hology.domain.model.CloudinaryResponse
import com.example.hology.domain.usecase.ExerciseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExerciseViewModel(private val cloudinaryUseCase: ExerciseUseCase) : ViewModel() {

    private val _imageUrl = MutableStateFlow<String?>(null)
    val imageUrl = _imageUrl.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    // function upload image
    fun uploadImage(uri: Uri) {
        viewModelScope.launch {
            _loading.value = true
            val result: CloudinaryResponse? = cloudinaryUseCase(uri)
            _imageUrl.value = result?.secureUrl
            _loading.value = false
        }
    }

    class Factory(private val exerciseUseCase: ExerciseUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ExerciseViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ExerciseViewModel(exerciseUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
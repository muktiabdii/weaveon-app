package com.example.hology.ui.wevy

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.hology.data.model.EmotionDetectionResponse
import com.example.hology.domain.usecase.WevyUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

// general state
sealed class WevyState {
    object Idle : WevyState()
    object Loading : WevyState()
    data class Success(val data: EmotionDetectionResponse) : WevyState()
    data class Error(val message: String) : WevyState()
}

class WevyViewModel(private val useCase: WevyUseCase) : ViewModel() {

    private val _state = MutableStateFlow<WevyState>(WevyState.Idle)
    val state: StateFlow<WevyState> = _state

    // function to detect emotion
    fun uploadVideo(file: File, userId: String, wevyId: String, activityId: String) {
        viewModelScope.launch {
            _state.value = WevyState.Loading
            try {
                val requestFile = file.asRequestBody("video/mp4".toMediaTypeOrNull())
                val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

                val result = useCase.detectEmotion(body)
                if (result != null) {
                    _state.value = WevyState.Success(result)
                    saveEmotion(userId, wevyId, activityId, result)
                } else {
                    _state.value = WevyState.Error("Gagal memproses video")
                }
            } catch (e: Exception) {
                Log.e("Wevy", "Upload failed: ${e.message}", e)
                _state.value = WevyState.Error(e.message ?: "Unknown error")
            }
        }
    }

    // function to save emotion
    fun saveEmotion(userId: String, wevyId: String, activityId: String, result: EmotionDetectionResponse) {
        viewModelScope.launch {
            useCase.saveEmotion(userId, wevyId, activityId, result)
        }
    }

    // function to get emotion
    fun getEmotion(userId: String, wevyId: String, activityId: String) {
        viewModelScope.launch {
            _state.value = WevyState.Loading
            try {
                val result = useCase.getEmotion(userId, wevyId, activityId)
                if (result != null) {
                    _state.value = WevyState.Success(result)
                } else {
                    _state.value = WevyState.Error("Data emotion tidak ditemukan")
                }
            } catch (e: Exception) {
                Log.e("Wevy", "Get emotion failed: ${e.message}", e)
                _state.value = WevyState.Error(e.message ?: "Unknown error")
            }
        }
    }

    class Factory(private val wevyUseCase: WevyUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WevyViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WevyViewModel(wevyUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

package com.example.hology.ui.exercise

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.hology.domain.model.CloudinaryResponse
import com.example.hology.domain.model.Exercise
import com.example.hology.domain.model.ExerciseHistoryUi
import com.example.hology.domain.model.ExerciseProgress
import com.example.hology.domain.usecase.ExerciseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// general state
sealed class ExerciseState {
    object Idle : ExerciseState()
    object Loading : ExerciseState()
    object Success : ExerciseState()
    data class Error(val message: String) : ExerciseState()
}

class ExerciseViewModel(private val exerciseUseCase: ExerciseUseCase) : ViewModel() {

    private val _imageUrl = MutableStateFlow<String?>(null)
    val imageUrl = _imageUrl.asStateFlow()

    private val _uploadState = MutableStateFlow<ExerciseState>(ExerciseState.Idle)
    val uploadState = _uploadState.asStateFlow()

    private val _saveState = MutableStateFlow<ExerciseState>(ExerciseState.Idle)
    val saveState = _saveState.asStateFlow()

    private val _exerciseProgress = MutableStateFlow<ExerciseProgress?>(null)
    val exerciseProgress: StateFlow<ExerciseProgress?> = _exerciseProgress.asStateFlow()

    private val _history = MutableStateFlow<List<ExerciseHistoryUi>>(emptyList())
    val history: StateFlow<List<ExerciseHistoryUi>> = _history


    // function upload image ke Cloudinary
    fun uploadImage(uri: Uri) {
        viewModelScope.launch {
            _uploadState.value = ExerciseState.Loading
            try {
                val result: CloudinaryResponse? = exerciseUseCase.invoke(uri)
                _imageUrl.value = result?.secureUrl
                if (result?.secureUrl != null) {
                    _uploadState.value = ExerciseState.Success
                } else {
                    _uploadState.value = ExerciseState.Error("Upload gagal, URL kosong")
                }
            } catch (e: Exception) {
                _uploadState.value = ExerciseState.Error(e.message ?: "Terjadi kesalahan")
            }
        }
    }

    // function simpan URL gambar ke DB
    fun saveImageUrl(userId: String, exerciseId: String, activityId: String) {
        viewModelScope.launch {
            _saveState.value = ExerciseState.Loading
            try {
                val url = _imageUrl.value ?: throw Exception("Image URL kosong")
                val result = exerciseUseCase.saveImageUrl(userId, exerciseId, activityId, url)
                if (result.isSuccess) {
                    _saveState.value = ExerciseState.Success
                } else {
                    _saveState.value =
                        ExerciseState.Error(result.exceptionOrNull()?.message ?: "Gagal menyimpan")
                }
            } catch (e: Exception) {
                _saveState.value = ExerciseState.Error(e.message ?: "Terjadi kesalahan")
            }
        }
    }

    // function load exercise progress
    fun loadExerciseProgress(exerciseId: String) {
        viewModelScope.launch {
            exerciseUseCase.getExerciseProgress(exerciseId).collect { progress ->
                _exerciseProgress.value = progress
                Log.d("ExerciseViewModel", "Exercise progress loaded: $progress")
            }
        }
    }

    // function load exercise history
    fun loadExerciseHistory(userId: String, exerciseList: List<Exercise>) {
        viewModelScope.launch {
            val result = exerciseUseCase.getExerciseHistory(userId)
            result.onSuccess { items ->
                val mapped = items.map { item ->
                    val exerciseTitle = exerciseList.find { it.id == item.exerciseId }?.title ?: item.exerciseId
                    val activityTitle = exerciseList
                        .find { it.id == item.exerciseId }
                        ?.activities?.find { it.id == item.activityId }
                        ?.title ?: item.activityId

                    ExerciseHistoryUi(
                        exerciseTitle = exerciseTitle,
                        activityTitle = activityTitle,
                        imageUrl = item.imageUrl,
                        date = formatDate(item.timestamp)
                    )
                }
                _history.value = mapped
            }.onFailure {
                Log.e("ExerciseViewModel", "Error loading exercise history", it)
            }
        }
    }

    // function date format
    private fun formatDate(timestamp: Long): String {
        val sdf = java.text.SimpleDateFormat("dd MMM yyyy", java.util.Locale("id", "ID"))
        return sdf.format(java.util.Date(timestamp))
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

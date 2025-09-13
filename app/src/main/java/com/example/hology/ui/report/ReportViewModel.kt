package com.example.hology.ui.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.hology.domain.model.ChartData
import com.example.hology.domain.usecase.WevyUseCase
import com.example.hology.ui.wevy.WevyViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// general state
sealed class ReportState{
    object Idle : ReportState()
    object Loading : ReportState()
    data class Success(val data: List<ChartData>) : ReportState()
    data class Error(val message: String) : ReportState()
}

class ReportViewModel(private val useCase: WevyUseCase) : ViewModel() {
    private val _state = MutableStateFlow<ReportState>(ReportState.Idle)
    val state: StateFlow<ReportState> = _state

    private val _chartData = MutableStateFlow<List<ChartData>>(emptyList())
    val chartData: StateFlow<List<ChartData>> = _chartData

    fun getCategoryChartData(userId: String) {
        viewModelScope.launch {
            _state.value = ReportState.Loading
            try {
                val result = useCase.getCategoryChartData(userId)
                _chartData.value = result
                _state.value = ReportState.Success(result)
            } catch (e: Exception) {
                _state.value = ReportState.Error(e.message ?: "Unknown error")
            }
        }
    }

    class Factory(private val wevyUseCase: WevyUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ReportViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ReportViewModel(wevyUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
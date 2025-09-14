package com.example.hology.ui.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.hology.domain.model.Category
import com.example.hology.domain.model.ChartData
import com.example.hology.domain.model.ReportTextState
import com.example.hology.domain.model.WevyHistoryItem
import com.example.hology.domain.usecase.WevyUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// graphic state
sealed class GraphicState{
    object Idle : GraphicState()
    object Loading : GraphicState()
    data class Success(val data: List<ChartData>) : GraphicState()
    data class Error(val message: String) : GraphicState()
}

// history state
sealed class HistoryState{
    object Idle : HistoryState()
    object Loading : HistoryState()
    data class Success(val data: List<WevyHistoryItem>) : HistoryState()
    data class Error(val message: String) : HistoryState()
}

class ReportViewModel(private val useCase: WevyUseCase) : ViewModel() {
    private val _graphicState = MutableStateFlow<GraphicState>(GraphicState.Idle)
    val graphicState: StateFlow<GraphicState> = _graphicState

    private val _historyState = MutableStateFlow<HistoryState>(HistoryState.Idle)
    val historyState: StateFlow<HistoryState> = _historyState

    private val _reportText = MutableStateFlow(ReportTextState())
    val reportText: StateFlow<ReportTextState> = _reportText

    // function to get category chart data
    fun getCategoryChartData(userId: String) {
        viewModelScope.launch {
            _graphicState.value = GraphicState.Loading
            try {
                val result = useCase.getCategoryChartData(userId)
                _graphicState.value = GraphicState.Success(result)
            } catch (e: Exception) {
                _graphicState.value = GraphicState.Error(e.message ?: "Unknown error")
            }
        }
    }

    // function to get conclusion
    fun generateReport(
        userId: String,
        allCategories: List<Category>,
    ) {
        viewModelScope.launch {
            try {
                val (conclusion, categoryId) = useCase.generateReport(userId, allCategories)
                _reportText.value = ReportTextState(
                    conclusion = conclusion,
                    categoryId = categoryId
                )
            } catch (e: Exception) {
                _reportText.value = ReportTextState(
                    conclusion = "Gagal memuat kesimpulan: ${e.message}",
                    categoryId = ""
                )
            }
        }
    }

    // function to get wevy history
    fun getWevyHistory(userId: String) {
        viewModelScope.launch {
            _historyState.value = HistoryState.Loading
            try {
                val result = useCase.getWevyHistory(userId)
                if (result.isSuccess) {
                    _historyState.value = HistoryState.Success(result.getOrThrow())
                } else {
                    _historyState.value = HistoryState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
                }
                } catch (e: Exception) {
                _historyState.value = HistoryState.Error(e.message ?: "Unknown error")
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
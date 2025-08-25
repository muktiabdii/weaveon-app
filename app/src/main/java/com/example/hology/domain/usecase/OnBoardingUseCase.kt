package com.example.hology.domain.usecase

import com.example.hology.data.datastore.PreferencesManager
import kotlinx.coroutines.flow.Flow

class OnBoardingUseCase(private val preferencesManager: PreferencesManager) {

    // function untuk mendapatkan state onboarding
    fun getOnBoardingState(): Flow<Boolean> {
        return preferencesManager.isOnboardingShown
    }

    // function untuk mengubah state onboarding
    suspend fun setOnBoardingState(shown: Boolean) {
        preferencesManager.setOnboardingShown(shown)
    }
}
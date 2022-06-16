package com.roshan.onboarding_presentation.nutrient_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.roshan.core.domain.preferences.Preferences
import com.roshan.core.domain.use_cases.FilterOutDigitsUseCase
import com.roshan.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigitsUseCase: FilterOutDigitsUseCase
) : ViewModel() {
    var state by mutableStateOf(NutrientGoalState())
        private set
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: NutrientGoalEvent) {
        when (event) {
            is NutrientGoalEvent.OnCarbRatioEnter -> {
                state = state.copy(carbsRatio = filterOutDigitsUseCase(event.ratio))
            }
            is NutrientGoalEvent.OnProteinRatioEnter -> {
                state = state.copy(proteinRatio = filterOutDigitsUseCase(event.ratio))
            }
            is NutrientGoalEvent.OnFatRatioEnter -> {
                state = state.copy(fatRatio = filterOutDigitsUseCase(event.ratio))
            }
            is NutrientGoalEvent.OnNextClick -> {

            }
        }

    }

}
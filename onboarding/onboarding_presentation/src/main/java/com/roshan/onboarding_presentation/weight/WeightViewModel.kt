package com.roshan.onboarding_presentation.weight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roshan.core.domain.preferences.Preferences
import com.roshan.core.domain.use_cases.FilterOutDigitsUseCase
import com.roshan.core.util.UiEvent
import com.roshan.core.util.UiText
import com.roshan.onboarding_presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigitsUseCase: FilterOutDigitsUseCase
) : ViewModel() {
    var weight by mutableStateOf("74")
        private set
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    fun onWeightEnter(weight: String) {
        if (weight.length <= 3) {
            this.weight = filterOutDigitsUseCase.invoke(weight)
        }
    }

    fun onNextClick() {
        viewModelScope.launch {
            val weightNumber = weight.toFloatOrNull() ?: kotlin.run {
                _uiEvent.send(UiEvent.ShowSnackBar(UiText.StringResources(R.string.error_weight_cant_be_empty)))
                return@launch
            }
            preferences.saveWeight(weightNumber)
            _uiEvent.send(UiEvent.Success)
        }

    }
}
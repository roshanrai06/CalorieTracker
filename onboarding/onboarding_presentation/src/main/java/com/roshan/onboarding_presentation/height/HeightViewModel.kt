package com.roshan.onboarding_presentation.height

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roshan.core.domain.preferences.Preferences
import com.roshan.core.domain.use_cases.FilterOutDigitsUseCase
import com.roshan.core.navigation.Route
import com.roshan.core.util.UiEvent
import com.roshan.core.util.UiText
import com.roshan.onboarding_presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigitsUseCase: FilterOutDigitsUseCase
) : ViewModel() {
    var height by mutableStateOf("0")
        private set
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    fun onHeightEnter(height: String) {
        if (height.length <= 3) {
            this.height = filterOutDigitsUseCase.invoke(height)
        }
    }

    fun onNextClick() {
        viewModelScope.launch {
            val heightNumber = height.toIntOrNull() ?: kotlin.run {
                _uiEvent.send(UiEvent.ShowSnackBar(UiText.StringResources(R.string.error_height_cant_be_empty)))
                return@launch
            }
            preferences.saveHeight(heightNumber)
            _uiEvent.send(UiEvent.Navigate(Route.WEIGHT))
        }

    }
}
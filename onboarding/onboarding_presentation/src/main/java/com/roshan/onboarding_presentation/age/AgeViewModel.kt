package com.roshan.onboarding_presentation.age

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
class AgeViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigitsUseCase: FilterOutDigitsUseCase
) : ViewModel() {
    var age by mutableStateOf("20")
        private set
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    fun onAgeEnter(age: String) {
        if (age.length <= 3) {
            this.age = filterOutDigitsUseCase.invoke(age)
        }
    }

    fun onNextClick() {
        viewModelScope.launch {
            val ageNumber = age.toIntOrNull() ?: kotlin.run {
                _uiEvent.send(UiEvent.ShowSnackBar(UiText.StringResources(R.string.error_age_cant_be_empty)))
                return@launch
            }
            preferences.saveAge(ageNumber)
            _uiEvent.send(UiEvent.Success)
        }

    }
}
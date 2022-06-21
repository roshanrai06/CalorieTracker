package com.roshan.onboarding_presentation.activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roshan.core.domain.model.ActivityLevel
import com.roshan.core.domain.preferences.Preferences
import com.roshan.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityScreenViewModel @Inject constructor(private val preferences: Preferences) :
    ViewModel() {
    var selectActivityLevel by mutableStateOf<ActivityLevel>(ActivityLevel.Medium)
        private set
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    fun onActivityLevelSelected(activityLevel: ActivityLevel) {
        selectActivityLevel = activityLevel
    }

    fun onNextClick() {
        viewModelScope.launch {
            preferences.saveActivityLevel(selectActivityLevel)
            _uiEvent.send(UiEvent.Success)
        }
    }

}
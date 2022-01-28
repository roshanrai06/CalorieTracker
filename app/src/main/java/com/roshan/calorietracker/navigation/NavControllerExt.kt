package com.roshan.calorietracker.navigation

import androidx.navigation.NavController
import com.roshan.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}
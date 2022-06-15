package com.roshan.onboarding_presentation.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.roshan.core.R
import com.roshan.core.navigation.Route
import com.roshan.core.util.UiEvent
import com.roshan.core_ui.LocalSpacing
import com.roshan.onboarding_presentation.components.ActionButton

@Composable
fun WelcomeScreen(onNavigate: (UiEvent.Navigate) -> Unit) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.welcome_text),
            textAlign = TextAlign.Center, style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = { onNavigate(UiEvent.Navigate(Route.GENDER)) },
            modifier = Modifier.align(CenterHorizontally)
        )
    }

}
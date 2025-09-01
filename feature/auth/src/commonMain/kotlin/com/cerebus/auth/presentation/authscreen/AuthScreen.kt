package com.cerebus.auth.presentation.authscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
public fun AuthScreenWrapper() {
    val viewModel = koinViewModel<AuthViewModel>()
    val state = viewModel.uiState.collectAsState()

    AuthScreen(state.value, viewModel)
}


@Composable
public fun AuthScreen(state: String, interactions: AuthInteractions) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Text(
            text = state, modifier = Modifier
                .clickable { interactions.loadUser() })
    }
}
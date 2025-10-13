package com.cerebus.auth.presentation.authscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
public fun AuthScreenWrapper(navController: NavHostController) {

    val navigator = AuthScreenNavigatorImpl(navController)
    val viewModel = koinViewModel<AuthViewModel>(
        parameters = { parametersOf(navigator) }
    )
    val state = viewModel.uiState.collectAsState()

    AuthScreen(state.value, viewModel)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun AuthScreen(state: AuthUiState, interactions: AuthInteractions) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var authMode by remember { mutableStateOf(AuthMode.LOGIN) }

    LaunchedEffect(state) {
        authMode = when (state) {
            is AuthUiState.VerifyEmail -> AuthMode.VERIFY
            else -> AuthMode.LOGIN
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing),
                title = { Text("Авторизация") },
                navigationIcon = {
                    IconButton(
                        onClick = { interactions.onBackPressed() },
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
            )
        },
        content = {
            AuthFields(authMode, interactions) { newMode -> authMode = newMode }

            when (state) {
                is AuthUiState.Authorization -> {  }
                is AuthUiState.VerifyEmail -> scope.launch {
                    snackbarHostState.showSnackbar(
                        message = state.message,
                        actionLabel = "OK"
                    )
                }

                is AuthUiState.SuccessAuthorization -> scope.launch {
                    snackbarHostState.showSnackbar(
                        message = state.message,
                        actionLabel = "OK"
                    )
                }
                is AuthUiState.Error -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = state.message,
                            actionLabel = "OK"
                        )
                    }
                }
            }
        }
    )
}

package com.cerebus.auth.presentation.authscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
            AuthFields(interactions)
            when (state) {
                is AuthUiState.Authorization -> {  }
                is AuthUiState.SuccessRegistration -> scope.launch {
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

@Composable
fun AuthFields(interactions: AuthInteractions) {

    Box(modifier = Modifier
        .fillMaxSize()
        .windowInsetsPadding(WindowInsets.safeDrawing),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp), // сделать скроллируемым, чтобы при необходимости подняться // ← контролирует IME через nested scroll
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var textLogin by remember { mutableStateOf("") }
            var textPass by remember { mutableStateOf("") }
            var checked by remember { mutableStateOf(false) }

            val buttonAction: (String, String) -> Unit
            val buttonText: String
            val switchDesc = "Регистрация"

            if (checked) {
                buttonAction = interactions::onRegister
                buttonText = "Зарегистрироваться"
            } else {
                buttonAction = interactions::onLogin
                buttonText = "Войти"
            }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                value = textLogin,
                onValueChange = { newText -> textLogin = newText },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                label = { Text("Логин") },
                placeholder = { Text("Введите ваш email") }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                value = textPass,
                onValueChange = { newText -> textPass = newText },
                label = { Text("Пароль") },
                placeholder = { Text("Введите пароль") }
            )

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = switchDesc)
                Switch(
                    checked = checked,
                    onCheckedChange = { checked = it }
                )
            }
            ElevatedButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = { buttonAction.invoke(textLogin, textPass) },
                content = {
                    Icon(Icons.AutoMirrored.Filled.Send, contentDescription = null)
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(buttonText)
                }
            )
        }
    }
}
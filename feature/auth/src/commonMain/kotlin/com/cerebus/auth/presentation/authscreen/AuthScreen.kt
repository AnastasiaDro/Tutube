package com.cerebus.auth.presentation.authscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import tutube.feature.auth.generated.resources.Res

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
public fun AuthScreen(state: String, interactions: AuthInteractions) {

    Scaffold(
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
        content = { AuthFields(interactions) }
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
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var textLogin by remember { mutableStateOf("") }
            var textPass by remember { mutableStateOf("") }

            OutlinedTextField(
                singleLine = true,
                value = textLogin,
                onValueChange = { newText -> textLogin = newText },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                label = { Text("Логин") },
                placeholder = { Text("Введите ваш email") }
            )

            OutlinedTextField(
                singleLine = true,
                value = textPass,
                onValueChange = { newText -> textPass = newText },
                label = { Text("Пароль") },
                placeholder = { Text("Введите пароль") }
            )

            ElevatedButton(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .widthIn(min = 200.dp),
                onClick = { interactions.onLogin(textLogin, textPass) },
                content = {
                    Icon(Icons.AutoMirrored.Filled.Send, contentDescription = null)
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Войти")
                }
            )
        }
    }

}
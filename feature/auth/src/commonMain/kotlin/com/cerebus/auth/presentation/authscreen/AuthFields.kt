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
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun AuthFields(authMode: AuthMode, interactions: AuthInteractions, onAuthModeChange: (newMOde: AuthMode) -> Unit) {

    val keyboardController = LocalSoftwareKeyboardController.current

    println("Настя перерисовка")

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

            val switchDesc = "Регистрация"

            val buttonText: String = when (authMode) {
                AuthMode.LOGIN -> "Войти"
                AuthMode.REGISTER -> "Зарегистрироваться"
                AuthMode.VERIFY -> "Подтвердить код"
            }

            /** Login **/
            AuthField(
                onValueChange = {
                    println("Настя onValueChange")
                    textLogin = it
                                },
                label = "email",
                placeHolderText = "Введите Ваш email",
                enabled = authMode != AuthMode.VERIFY,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            )

            /** Password **/
            AnimatedVisibility(visible = authMode != AuthMode.REGISTER) {
                val text = if (authMode == AuthMode.VERIFY) "Введите код" else "Введите пароль"
                val label = if (authMode == AuthMode.VERIFY) "Код" else "Пароль"

                AuthField(
                    onValueChange = { textPass = it },
                    label = label,
                    placeHolderText = text,
                )
            }

            AnimatedVisibility(visible = authMode != AuthMode.VERIFY) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = switchDesc)
                    Switch(
                        checked = authMode == AuthMode.REGISTER,
                        onCheckedChange = { isRegister ->
                            val newAuthMode = if (isRegister) AuthMode.REGISTER else AuthMode.LOGIN
                            onAuthModeChange(newAuthMode)
                        }
                    )
                }
            }

            ElevatedButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    when(authMode) {
                        AuthMode.LOGIN -> interactions.onLogin(textLogin, textPass)
                        AuthMode.REGISTER -> interactions.onRegister(textLogin)
                        AuthMode.VERIFY -> interactions.onVerify(textLogin, textPass)
                    }
                    keyboardController?.hide()
                },
                content = {
                    Icon(Icons.AutoMirrored.Filled.Send, contentDescription = null)
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(buttonText)
                }
            )
        }
    }
}


@Composable
fun AuthField(
    onValueChange: (String) -> Unit,
    label: String,
    placeHolderText: String,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    var textPass by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        value = textPass,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        onValueChange = {
            newText -> textPass = newText
            onValueChange.invoke(newText)
                        },
        label = { Text(label) },
        placeholder = { Text(placeHolderText) }
    )
}
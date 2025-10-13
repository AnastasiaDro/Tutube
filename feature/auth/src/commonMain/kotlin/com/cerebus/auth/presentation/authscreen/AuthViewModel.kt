package com.cerebus.auth.presentation.authscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerebus.auth.domain.usecases.AuthorizeUserUseCase
import com.cerebus.auth.domain.usecases.LoadUserUseCase
import com.cerebus.auth.domain.usecases.RegisterUserUseCase
import com.cerebus.auth.domain.usecases.VerifyUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class AuthViewModel(
    private val navigator: AuthScreenNavigator,
    private val authorizeUserUseCase: AuthorizeUserUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val verifyUserUseCase: VerifyUserUseCase,
    private val loadUserUseCase: LoadUserUseCase,
) : ViewModel(), AuthInteractions {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Authorization)
    val uiState: StateFlow<AuthUiState> = _uiState

    override fun loadUser() {
        viewModelScope.launch {
            loadUserUseCase.execute().collect { user ->
                println("Настя user = $user загружен")
            }
        }
    }

    override fun onLogin(login: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            authorizeUserUseCase.execute(login, pass).collect { user ->
                //TODO обработка пустого user-а
                if (user == null) {
                    _uiState.emit(AuthUiState.Error("Ошибка авторизации"))
                } else {
                    _uiState.emit(AuthUiState.SuccessAuthorization("Успешный вход!"))
                }
            }
        }
    }

    override fun onRegister(login: String) {
        viewModelScope.launch(Dispatchers.IO) {
            registerUserUseCase.execute(login).collect { result ->
                if (result) {
                    _uiState.emit(AuthUiState.VerifyEmail("Успешная регистрация!"))
                } else {
                    _uiState.emit(AuthUiState.Error("Ошибка регистрации"))
                }
            }
        }
    }

    override fun onVerify(login: String, code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            verifyUserUseCase.execute(login, code).collect { user ->
                if (user != null) {
                    navigator.goToUserData()
                } else {
                    _uiState.emit(AuthUiState.Error("Ошибка верификации"))
                }
            }
        }
    }

    override fun onCheckCreds() {
        TODO("Not yet implemented")
    }

    override fun onBackPressed() {
        navigator.goBack()
    }
}
package com.cerebus.auth.presentation.authscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerebus.auth.domain.usecases.AuthorizeUserUseCase
import com.cerebus.auth.domain.usecases.RegisterUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class AuthViewModel(
    private val navigator: AuthScreenNavigator,
    private val authorizeUserUseCase: AuthorizeUserUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
) : ViewModel(), AuthInteractions {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.SelectWay)
    val uiState: StateFlow<AuthUiState> = _uiState

    override fun loadUser() {
        viewModelScope.launch {
           // delay(1000)
            _uiState.emit(AuthUiState.Error("Tmp error"))
        }
    }

    override fun onLogin(login: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            authorizeUserUseCase.execute(login, pass).collect {
                //TODO обработка пустого user-а
                _uiState.emit(AuthUiState.SuccessAuthorization("Успешный вход!"))
            }
        }
    }

    override fun onRegister(login: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            registerUserUseCase.execute(login, pass).collect {
                _uiState.emit(AuthUiState.SuccessRegistration("Успешная регистрация!"))
            }
        }
    }

    override fun onBackPressed() {
        navigator.goBack()
    }

    override fun selectLogin() {
        viewModelScope.launch {
            _uiState.emit(AuthUiState.Authorization)
        }
    }

    override fun selectRegister() {
        viewModelScope.launch {
            _uiState.emit(AuthUiState.Registration)
        }
    }
}
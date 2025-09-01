package com.cerebus.auth.presentation.authscreen

import androidx.lifecycle.viewModelScope
import com.cerebus.auth.presentation.api.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class AuthViewModel : BaseViewModel(), AuthInteractions {
    private val _uiState = MutableStateFlow<String>("I am user")
    val uiState: StateFlow<String> = _uiState

    override fun loadUser() {
        viewModelScope.launch {
            delay(1000)
            _uiState.emit("I am user 1")
        }
    }
}
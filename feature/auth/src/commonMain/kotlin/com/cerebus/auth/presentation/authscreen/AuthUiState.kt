package com.cerebus.auth.presentation.authscreen

sealed interface AuthUiState {
    public data object Initial : AuthUiState
    public data object Loading : AuthUiState
    public data class Error(val message: String) : AuthUiState
}
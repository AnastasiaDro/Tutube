package com.cerebus.auth.presentation.authscreen

sealed interface AuthUiState {
    public data object Authorization : AuthUiState
    public data class SuccessAuthorization(val message: String) : AuthUiState
    public data class SuccessRegistration(val message: String) : AuthUiState
   // public data object Loading : AuthUiState
    public data class Error(val message: String) : AuthUiState
}
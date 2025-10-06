package com.cerebus.auth.creds

sealed interface CredentialsSignUpResult {
    data class Success(val userName: String) : CredentialsSignUpResult
    data object Cancelled: CredentialsSignUpResult
    data object Failure: CredentialsSignUpResult
}
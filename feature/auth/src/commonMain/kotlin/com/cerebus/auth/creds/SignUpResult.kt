package com.cerebus.auth.creds

sealed interface SignUpResult {
    data class Success(val userName: String) : SignUpResult
    data object Cancelled: SignUpResult
    data object Failure: SignUpResult
}
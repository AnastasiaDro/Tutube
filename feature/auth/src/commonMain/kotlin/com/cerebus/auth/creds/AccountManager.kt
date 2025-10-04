package com.cerebus.auth.creds

interface AccountManager {

    suspend fun signUp(userName: String, password: String): CredentialsSignUpResult
    suspend fun signIn(userName: String, password: String): SignInResult
}
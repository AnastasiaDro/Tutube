package com.cerebus.auth.creds

interface AccountManager {

    suspend fun signUp(userName: String, password: String): SignUpResult
    suspend fun signIn(userName: String, password: String): SignInResult
}
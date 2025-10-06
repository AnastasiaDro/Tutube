package com.cerebus.auth

import com.cerebus.auth.creds.AccountManager
import com.cerebus.auth.creds.CredentialsSignUpResult
import com.cerebus.auth.creds.SignInResult

class AccountManageriOSImpl() : AccountManager {
    override suspend fun signUp(
        userName: String,
        password: String
    ): CredentialsSignUpResult {
        TODO("Not yet implemented")
    }

    override suspend fun signIn(
        userName: String,
        password: String
    ): SignInResult {
        TODO("Not yet implemented")
    }

}
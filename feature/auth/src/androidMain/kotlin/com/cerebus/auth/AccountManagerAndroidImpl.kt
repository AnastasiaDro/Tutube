package com.cerebus.auth

import android.content.Context
import androidx.credentials.CreatePasswordRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPasswordOption
import androidx.credentials.PasswordCredential
import androidx.credentials.exceptions.CreateCredentialCancellationException
import androidx.credentials.exceptions.CreateCredentialException
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import com.cerebus.auth.creds.AccountManager
import com.cerebus.auth.creds.SignInResult
import com.cerebus.auth.creds.CredentialsSignUpResult

class AccountManagerAndroidImpl(
    private val context: Context
) : AccountManager {
    private val credsManager = CredentialManager.create(context)

    override suspend fun signUp(userName: String, password: String): CredentialsSignUpResult {
        return try {
            credsManager.createCredential(
                context = context,
                request = CreatePasswordRequest(
                    id = userName,
                    password = password
                )
            )
            CredentialsSignUpResult.Success(userName)
        } catch (e: CreateCredentialCancellationException) {
            e.printStackTrace()
            CredentialsSignUpResult.Cancelled
        } catch (e: CreateCredentialException) {
            e.printStackTrace()
            CredentialsSignUpResult.Failure
        }
    }

    override suspend fun signIn(userName: String, password: String): SignInResult {
        return try {
            val credentialResponse = credsManager.getCredential(
                context = context,
                request = GetCredentialRequest(
                    credentialOptions = listOf(GetPasswordOption())
                )
            )

            val credential = credentialResponse.credential as? PasswordCredential
                ?: return SignInResult.Failure

            // Make login API call here with credential.id and credential.password

            SignInResult.Success(credential.id)
        } catch (e: GetCredentialCancellationException) {
            e.printStackTrace()
            SignInResult.Cancelled
        } catch (e: NoCredentialException) {
            e.printStackTrace()
            SignInResult.NoCredentials
        } catch (e: GetCredentialException) {
            e.printStackTrace()
            SignInResult.Failure
        }
    }
}
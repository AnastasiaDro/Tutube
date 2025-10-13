package com.cerebus.auth.data.storage

import com.cerebus.network.userData.UserDto
import com.cerebus.network.userData.VerifyUserDto

interface UserStorage {

    suspend fun registerUser(userName: String): StorageResponse<Unit?>

    suspend fun verifyUser(email: String, code: String): StorageResponse<VerifyResult>

    suspend fun getUserByToken(token: String, userName: String): StorageResponse<UserDto>

    suspend fun updateUser(token: String, userData: UserDto): StorageResponse<UserDto>

    suspend fun loginUser(login: String, pass: String): StorageResponse<LoginResult>
}

data class VerifyResult(val user: UserDto?, val token: String?)

data class LoginResult(val user: UserDto?, val token: String?)

sealed class StorageResponse<out T>() {
    data class Success<out T>(val result: T?): StorageResponse<T>()

    data class Error(val message: String, val errorType: String): StorageResponse<Nothing>()
}
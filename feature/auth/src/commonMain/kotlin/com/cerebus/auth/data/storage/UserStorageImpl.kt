package com.cerebus.auth.data.storage

import com.cerebus.network.api.UserApi
import com.cerebus.network.userData.AuthUserDto
import com.cerebus.network.userData.GetUserDto
import com.cerebus.network.userData.RegisterUserDto
import com.cerebus.network.userData.UserDto
import com.cerebus.network.userData.VerifyUserDto

class UserStorageImpl(private val userApi: UserApi) : UserStorage {

    override suspend fun getUserByToken(token: String, userName: String): StorageResponse<UserDto> {
        val response = userApi.getUserByToken(token, GetUserDto(userName))
        return response?.let { result ->
            if (result.success) {
                StorageResponse.Success<UserDto>(result.data)
            } else {
                StorageResponse.Error(result.message, result.errorType ?: "")
            }
        } ?: StorageResponse.Error("Internal Error", "Internal Error")
    }

    override suspend fun registerUser(userName: String): StorageResponse<Unit?> {
        val response = userApi.registerUser(RegisterUserDto(userName))
        return response?.let { result ->
            if (result.success) {
                StorageResponse.Success<Unit?>(null)
            } else {
                StorageResponse.Error(result.message, result.errorType ?: "")
            }
        } ?: StorageResponse.Error("Internal Error", "Internal Error")
    }

    override suspend fun verifyUser(email: String, code: String): StorageResponse<VerifyResult> {
        val response = userApi.verifyUser(VerifyUserDto(email, code))
        return response?.let { result ->
            if (result.success) {
                StorageResponse.Success<VerifyResult>(VerifyResult(user = result.data, token = result.token))
            } else {
                StorageResponse.Error(result.message, result.errorType ?: "")
            }
        } ?: StorageResponse.Error("Internal Error", "Internal Error")
    }

    override suspend fun updateUser(token: String, userData: UserDto): StorageResponse<UserDto>  {
        val response = userApi.updateUser(token, userData)
        return response?.let { result ->
            if (result.success) {
                StorageResponse.Success<UserDto>(result.data)
            } else {
                StorageResponse.Error(result.message, result.errorType ?: "")
            }

        } ?: StorageResponse.Error("Internal Error", "Internal Error")
    }

    override suspend fun loginUser(
        login: String,
        pass: String
    ): StorageResponse<LoginResult> {
        val response = userApi.loginUser(AuthUserDto(login, pass))
        return response?.let { result ->
            if (result.success) {
                StorageResponse.Success<LoginResult>(LoginResult(user = result.data, token = result.token))
            } else {
                StorageResponse.Error(result.message, result.errorType ?: "")
            }

        } ?: StorageResponse.Error("Internal Error", "Internal Error")
    }
}


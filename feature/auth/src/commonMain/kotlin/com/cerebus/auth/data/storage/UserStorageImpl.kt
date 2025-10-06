package com.cerebus.auth.data.storage

import com.cerebus.network.api.UserApi
import com.cerebus.network.userData.CreateUserDto
import com.cerebus.network.userData.UserDto
import com.cerebus.utils.api.logger

class UserStorageImpl(private val userApi: UserApi) : UserStorage {
    override suspend fun getUserByToken(token: String, userName: String): UserDto? {
        return userApi.getUserByToken(token, userName)
    }

    override suspend fun registerUser(createUserData: CreateUserDto): String? {
        val answer = userApi.registerUser(createUserData)
        return answer?.token
    }

    override suspend fun fillUser(token: String, userData: UserDto): UserDto? {
        return userApi.fillUser(token, userData)
    }

    override suspend fun loginUser(
        login: String,
        pass: String
    ): String? {
        val answer = userApi.loginUser(login, pass)

        return answer?.token
    }


}
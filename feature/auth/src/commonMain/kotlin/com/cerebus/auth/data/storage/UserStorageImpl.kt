package com.cerebus.auth.data.storage

import com.cerebus.network.api.UserApi
import com.cerebus.network.userData.CreateUserDto
import com.cerebus.network.userData.UserDto

class UserStorageImpl(private val userApi: UserApi) : UserStorage {
    override suspend fun getUserByToken(token: String): UserDto {
        return userApi.getUserByToken(token)
    }

    override suspend fun registerUser(createUserData: CreateUserDto): String {
        return userApi.registerUser(createUserData)
    }

    override suspend fun fillUser(userData: UserDto): UserDto {
        return userApi.fillUser(userData)
    }

    override suspend fun loginUser(
        login: String,
        pass: String
    ): UserDto {
        return userApi.loginUser(login, pass)
    }


}
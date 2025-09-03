package com.cerebus.network.api

import com.cerebus.network.userData.AuthResponse
import com.cerebus.network.userData.CreateUserDto
import com.cerebus.network.userData.UserDto

interface UserApi {

    suspend fun getUserByToken(token: String, userName: String): UserDto?

    suspend fun registerUser(createUserData: CreateUserDto): AuthResponse?

    suspend fun fillUser(token: String, userData: UserDto): UserDto?

    suspend fun loginUser(login: String, pass: String): AuthResponse?
}
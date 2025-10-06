package com.cerebus.auth.data.storage

import com.cerebus.network.userData.CreateUserDto
import com.cerebus.network.userData.UserDto

interface UserStorage {

    suspend fun getUserByToken(token: String, userName: String): UserDto?

    suspend fun registerUser(createUserData: CreateUserDto): String?

    suspend fun fillUser(token: String, userData: UserDto): UserDto?

    suspend fun loginUser(login: String, pass: String): String?
}
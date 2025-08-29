package com.cerebus.network.api

import com.cerebus.network.userData.CreateUserDto
import com.cerebus.network.userData.UserDto

interface UserApi {

    suspend fun getUserById(id: String): UserDto

    suspend fun registerUser(createUserData: CreateUserDto): UserDto

    suspend fun fillUser(userData: UserDto): UserDto
}
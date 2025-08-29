package com.cerebus.network.userData

interface UserApi {

    suspend fun getUserById(id: String): UserDto

    suspend fun registerUser(createUserData: CreateUserDto): UserDto

    suspend fun fillUser(userData: UserDto): UserDto
}
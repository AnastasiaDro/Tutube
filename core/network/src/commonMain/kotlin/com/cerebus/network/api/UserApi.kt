package com.cerebus.network.api

import com.cerebus.network.userData.AuthResponse
import com.cerebus.network.userData.CreateUserDto
import com.cerebus.network.userData.UserDto

interface UserApi {

    suspend fun getUserByToken(id: String): UserDto?

    suspend fun registerUser(createUserData: CreateUserDto): String

    suspend fun fillUser(userData: UserDto): UserDto

    suspend fun loginUser(login: String, pass: String): AuthResponse?

    //public static class LoginRequest {
    //    private String email;    private String password;
    //}
}
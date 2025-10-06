package com.cerebus.network.userData

import kotlinx.serialization.Serializable

//это я отправляю для регистрации

/**
 * @created by Anastasia Drogunova
 * on 29.08.2025
 */

@Serializable
data class CreateUserDto(val userName: String)

@Serializable
data class UserByTokenRequest(val token: String, val userName: String)

@Serializable
data class UserDto(
    val userName: String, //тут login
    val firstName: String? = null,
    val lastName: String? = null,
    val age: Double? = null,
    val factAge: Double? = null,
    val currentLevel: Int = 1,
    val successRate: Int? = null,
    val tryingTimes: Int? = null,
)

@Serializable
data class AuthResponse(
    val message: String,
    val success: Boolean,
    val token: String?
)

@Serializable
data class AuthRequest(
    val userName: String,
    val password: String
)

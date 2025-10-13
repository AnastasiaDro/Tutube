package com.cerebus.network.userData

import kotlinx.serialization.Serializable

//это я отправляю для регистрации

/**
 * @created by Anastasia Drogunova
 * on 29.08.2025
 */

/** Requests **/
@Serializable
data class RegisterUserDto(val userName: String)

@Serializable
data class VerifyUserDto(val email: String, val code: String)

@Serializable
data class UserByTokenRequest(val token: String, val userName: String)

@Serializable
data class AuthUserDto(
    val userName: String,
    val password: String
)

/** Responces **/

@Serializable
data class Response<T>(
    val message: String,
    val success: Boolean,
    val data: T? = null,
    val errorType: String? = null,
    val token: String? = null,
)
@Serializable
data class UserDto(
    val userName: String, //тут login
    val firstName: String? = null,
    val lastName: String? = null,
    val birthDate: String? = null,
    val factAge: Double? = null,
    val currentLevel: Int = 1,
    val successRate: Int? = null,
    val tryingTimes: Int? = null,
)

@Serializable
data class GetUserDto(
    val userName: String
)


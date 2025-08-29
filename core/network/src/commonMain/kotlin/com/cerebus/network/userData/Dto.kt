package com.cerebus.network.userData

import kotlinx.serialization.Serializable

//это я отправляю для регистрации

/**
 * @created by Anastasia Drogunova
 * on 29.08.2025
 */

@Serializable
sealed class RequestsDto

@Serializable
data class CreateUserDto(val email: String, val password: String) : RequestsDto()

@Serializable
sealed class ResponsesDto

@Serializable
data class UserDto(
    val id: String,
    val firstName: String,
    val lastName: String,
    val age: Double,
    val factAge: Double? = null,
    val currentLevel: Int = 1,
    val successRate: Int? = null,
    val tryingTimes: Int? = null,
) : ResponsesDto()

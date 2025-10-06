package com.cerebus.auth.domain.repository

import com.cerebus.auth.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUserByToken(): User?

    //TODO: Пока у нас на беке при регистрации нужен только email,
    //На почту придет одноразовый пароль
    //suspend fun registerUser(email: String, pass: String): String?
    suspend fun registerUser(email: String): String?
    suspend fun authorizeUser(email: String, pass: String): Boolean

    suspend fun fillUser(userData: User): User?
}
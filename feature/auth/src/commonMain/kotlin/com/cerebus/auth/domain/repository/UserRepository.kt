package com.cerebus.auth.domain.repository

import com.cerebus.auth.domain.models.User

interface UserRepository {

    fun getUserName(): String?

    fun getToken(): String?

    suspend fun getUserByToken(email: String, token: String): User?

    //TODO: Пока у нас на беке при регистрации нужен только email,
    //На почту придет одноразовый пароль
    //suspend fun registerUser(email: String, pass: String): String?
    suspend fun registerUser(email: String): Boolean

    suspend fun verifyUser(email: String, code: String): User?
    suspend fun authorizeUser(email: String, pass: String): User?

    suspend fun updateUser(newUserData: User): User?
}
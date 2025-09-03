package com.cerebus.auth.domain.repository

import com.cerebus.auth.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUserByToken(): User?

    suspend fun registerUser(email: String, pass: String): String?
    suspend fun authorizeUser(email: String, pass: String): Boolean

    suspend fun fillUser(userData: User): User?
}
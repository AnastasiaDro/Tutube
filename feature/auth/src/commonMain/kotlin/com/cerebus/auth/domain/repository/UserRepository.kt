package com.cerebus.auth.domain.repository

import com.cerebus.auth.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser(token: String): Flow<User>

    fun registerUser(email: String, pass: String): Flow<Boolean>
    fun authorizeUser(email: String, pass: String): Flow<User>

    fun fillUser(): Flow<User>
}
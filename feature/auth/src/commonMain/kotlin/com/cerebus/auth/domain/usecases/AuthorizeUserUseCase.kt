package com.cerebus.auth.domain.usecases

import com.cerebus.auth.domain.models.User
import com.cerebus.auth.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthorizeUserUseCase(private val userRepository: UserRepository) {

    fun execute(login: String, pass: String): Flow<User?> = flow {
        val token = userRepository.authorizeUser(login, pass)
        val user = userRepository.getUserByToken(token)
        emit(user)
    }
}
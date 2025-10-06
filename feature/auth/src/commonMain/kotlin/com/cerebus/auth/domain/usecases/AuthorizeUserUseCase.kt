package com.cerebus.auth.domain.usecases

import com.cerebus.auth.domain.models.User
import com.cerebus.auth.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthorizeUserUseCase(private val userRepository: UserRepository) {

    fun execute(login: String, pass: String): Flow<Boolean?> = flow {
        val res = userRepository.authorizeUser(login, pass)
        println("Настя результат авторизации $res")
        emit(res)
    }
}
package com.cerebus.auth.domain.usecases

import com.cerebus.auth.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterUserUseCase(private val userRepository: UserRepository) {
    fun execute(login: String, pass: String): Flow<Boolean> = flow {
        val token = userRepository.registerUser(login, pass)
        val result = token != null && token.isNotBlank()
         //сохранить токен
        emit(result)
    }
}
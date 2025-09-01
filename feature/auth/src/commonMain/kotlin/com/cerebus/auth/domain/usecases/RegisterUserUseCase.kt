package com.cerebus.auth.domain.usecases

import com.cerebus.auth.domain.models.User
import com.cerebus.auth.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class RegisterUserUseCase(private val userRepository: UserRepository) {

    fun execute(login: String, pass: String): Flow<Boolean> {
        return userRepository.registerUser(login, pass)
    }
}
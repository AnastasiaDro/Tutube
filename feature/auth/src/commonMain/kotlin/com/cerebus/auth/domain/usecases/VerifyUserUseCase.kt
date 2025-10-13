package com.cerebus.auth.domain.usecases

import com.cerebus.auth.domain.models.User
import com.cerebus.auth.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VerifyUserUseCase(private val userRepository: UserRepository) {

    fun execute(email: String, code: String): Flow<User?> = flow {
        val user = userRepository.verifyUser(email, code)
        emit(user)
    }
}
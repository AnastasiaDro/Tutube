package com.cerebus.auth.domain.usecases

import com.cerebus.auth.domain.models.User
import com.cerebus.auth.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoadUserUseCase(private val userRepository: UserRepository) {

    fun execute(): Flow<User?> = flow {
        //TODO Переделать
        val email = userRepository.getUserName()
        val token = userRepository.getToken()

        if (email != null && token != null) {
            val user = userRepository.getUserByToken(email, token)
            emit(user)
        }
    }
}

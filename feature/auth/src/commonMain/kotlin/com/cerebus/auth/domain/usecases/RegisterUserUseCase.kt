package com.cerebus.auth.domain.usecases

import com.cerebus.auth.creds.AccountManager
import com.cerebus.auth.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterUserUseCase(private val userRepository: UserRepository, private val accountManager: AccountManager) {
    fun execute(login: String): Flow<Boolean> = flow {
        val isSuccess = userRepository.registerUser(login)
        emit(isSuccess)
    }
}
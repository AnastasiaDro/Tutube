package com.cerebus.auth.domain.usecases

import com.cerebus.auth.creds.AccountManager
import com.cerebus.auth.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterUserUseCase(private val userRepository: UserRepository, private val accountManager: AccountManager) {
    fun execute(login: String, pass: String): Flow<Boolean> = flow {

       // val token = userRepository.registerUser(login, pass)
        //TODO надо посмотреть, что тут в итоге с авторизацией
        val token = userRepository.registerUser(login)
        val result = token != null && token.isNotBlank()
         //сохранить токен
        if (result) accountManager.signUp(login, pass)

        emit(result)
    }
}
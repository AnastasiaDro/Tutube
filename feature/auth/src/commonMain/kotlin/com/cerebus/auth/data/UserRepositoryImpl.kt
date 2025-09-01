package com.cerebus.auth.data

import com.cerebus.auth.data.storage.UserStorage
import com.cerebus.auth.domain.models.User
import com.cerebus.auth.domain.repository.UserRepository
import com.cerebus.network.userData.CreateUserDto
import com.cerebus.network.userData.UserDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(private val storage: UserStorage) : UserRepository {
    override fun getUser(token: String): Flow<User> = flow {
        val data = storage.getUserByToken(token)
        emit(userDtoToUser(data))
    }

    override fun registerUser(
        email: String,
        pass: String
    ): Flow<Boolean> = flow {
        val token = storage.registerUser(CreateUserDto(email, pass))
        //здест сохраняем токен, в зависимости от успеха возвращаем
        emit(true)
    }

    override fun authorizeUser(
        email: String,
        pass: String
    ): Flow<User> {
        TODO("Not yet implemented")
    }

    override fun fillUser(): Flow<User> {
        TODO("Not yet implemented")
    }

    private fun userDtoToUser(userDto: UserDto): User {
        return User(
            id = userDto.id,
            firstName = userDto.firstName,
            lastName = userDto.lastName,
            age = userDto.age,
            factAge = userDto.factAge,
            currentLevel = userDto.currentLevel,
            successRate = userDto.successRate,
            tryingTimes = userDto.tryingTimes,
        )
    }
}
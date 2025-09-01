package com.cerebus.auth.data

import com.cerebus.auth.data.storage.UserStorage
import com.cerebus.auth.domain.models.User
import com.cerebus.auth.domain.repository.UserRepository
import com.cerebus.network.userData.CreateUserDto
import com.cerebus.network.userData.UserDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(private val storage: UserStorage) : UserRepository {

    override suspend fun getUserByToken(token: String): User? {
        val data = storage.getUserByToken(token)
        return data?.let { userDtoToUser(it) }
    }

    override suspend fun registerUser(
        email: String,
        pass: String
    ): String  {
        val token = storage.registerUser(CreateUserDto(email, pass))
        return token
    }

    override suspend fun authorizeUser(
        email: String,
        pass: String
    ): String {
        val token = storage.loginUser(email, pass)
        return token
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
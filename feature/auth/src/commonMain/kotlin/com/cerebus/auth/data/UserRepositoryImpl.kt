package com.cerebus.auth.data

import com.cerebus.auth.data.storage.UserStorage
import com.cerebus.auth.domain.models.User
import com.cerebus.auth.domain.repository.UserRepository
import com.cerebus.network.userData.CreateUserDto
import com.cerebus.network.userData.UserDto

class UserRepositoryImpl(private val storage: UserStorage) : UserRepository {
    private var userName = "aaa@test.ru"
    private var token: String? = ""

    override suspend fun getUserByToken(): User? {
        val data = token?.let { t -> storage.getUserByToken(t, userName) }
        return data.toUser()
    }

    override suspend fun registerUser(
        email: String,
        pass: String
    ): String? {
        val t = storage.registerUser(CreateUserDto(email, pass))
        token = t
        return t
    }

    override suspend fun authorizeUser(
        email: String,
        pass: String
    ): Boolean {
        token = storage.loginUser(email, pass)
        return !token.isNullOrBlank()
    }

    //TODO сделать multilet
    override suspend fun fillUser(userData: User): User? {
        return (token?.let { t ->
            userData.toUserDto() ?.let { dto ->
                storage.fillUser(t, dto)
            }
        }).toUser()
    }

    private fun UserDto?.toUser(): User? {
        if (this == null) return null
        return User(
            userName = userName,
            firstName = this.firstName,
            lastName = this.lastName,
            age = this.age,
            factAge = this.factAge,
            currentLevel = this.currentLevel,
            successRate = this.successRate,
            tryingTimes = this.tryingTimes,
        )
    }

    private fun User?.toUserDto(): UserDto? {
        if (this == null) return null
        return UserDto(
            userName = userName,
            firstName = this.firstName,
            lastName = this.lastName,
            age = this.age,
            factAge = this.factAge,
            currentLevel = this.currentLevel,
            successRate = this.successRate,
            tryingTimes = this.tryingTimes,
        )
    }
}
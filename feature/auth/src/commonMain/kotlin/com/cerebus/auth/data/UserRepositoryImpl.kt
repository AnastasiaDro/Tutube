package com.cerebus.auth.data

import com.cerebus.auth.data.storage.LoginResult
import com.cerebus.auth.data.storage.StorageResponse
import com.cerebus.auth.data.storage.UserStorage
import com.cerebus.auth.data.storage.VerifyResult
import com.cerebus.auth.domain.models.User
import com.cerebus.auth.domain.repository.UserRepository
import com.cerebus.network.userData.UserDto
import com.cerebus.utils.api.logger

/**
 * [UserRepositoryImpl] реализует [UserRepository]
 * класс для работы с данными авторизации
 * @author Anastasia Drogunova
 * @since 01.09.2025
 */
class UserRepositoryImpl(private val storage: UserStorage) : UserRepository {
    private var userName = "aaa@test.ru"
        set(value) {
            logger.d(moduleTag = "AUTH", tag = "userName", message = { "new USER_NAME = $value" })
            field = value
        }

    override fun getUserName() = userName

    private var token: String? = null
        set(value) {
            logger.d(moduleTag = "AUTH", tag = "token", message = { "new TOKEN = $value" })
            field = value
        }
    override fun getToken() = token



    override suspend fun getUserByToken(email: String, token: String): User? {
        val data = token?.let { t -> storage.getUserByToken(t, userName) }
        return if (data is StorageResponse.Success) {
           data.result.toUser()
        } else {
            null
        }
    }

    override suspend fun registerUser(
        email: String,
    ): Boolean {
        val result = storage.registerUser(email)
        val res = result is StorageResponse.Success
        return res
    }

    override suspend fun verifyUser(email: String, code: String): User? {
        logger.d(moduleTag = "AUTH", tag = "verifyUser", message = { "email: $email, code: $code" })
        val data = storage.verifyUser(email, code)
        return if (data is StorageResponse.Success) {
            val verifyResult = data.result
            token = verifyResult?.token
            userName = email
            verifyResult?.user.toUser()
        } else {
            null
        }
    }

    override suspend fun authorizeUser(
        email: String,
        pass: String
    ): User? {
        val data = storage.loginUser(email, pass)

        return if (data is StorageResponse.Success) {
            val authResult = data.result
            token = authResult?.token
            authResult?.user.toUser()
        } else {
            null
        }
    }

    override suspend fun updateUser(newUserData: User): User? {
        val t = token
        val userDto = newUserData.toUserDto()

        if (!t.isNullOrBlank() && userDto != null) {
            val data = storage.updateUser(t, userDto)
            if (data is StorageResponse.Success) {
                return data.result?.toUser()
            }
        }
        return null
    }

    private fun UserDto?.toUser(): User? {
        if (this == null) return null
        return User(
            userName = userName,
            firstName = this.firstName,
            lastName = this.lastName,
            birthDate = this.birthDate,
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
            birthDate = this.birthDate,
            factAge = this.factAge,
            currentLevel = this.currentLevel,
            successRate = this.successRate,
            tryingTimes = this.tryingTimes,
        )
    }
}

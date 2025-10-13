package com.cerebus.auth.data

import com.cerebus.auth.data.storage.LoginResult
import com.cerebus.auth.data.storage.StorageResponse
import com.cerebus.auth.data.storage.UserStorage
import com.cerebus.auth.data.storage.VerifyResult
import com.cerebus.auth.domain.models.User
import com.cerebus.auth.domain.repository.UserRepository
import com.cerebus.network.userData.UserDto

/**
 * [UserRepositoryImpl] реализует [UserRepository]
 * класс для работы с данными авторизации
 * @author Anastasia Drogunova
 * @since 01.09.2025
 */
class UserRepositoryImpl(private val storage: UserStorage) : UserRepository {
    private var userName = "aaa@test.ru"
        set(value) {
            println("Настя присвоен userName = $value")
            field = value
        }
    private var token: String? = null
        set(value) {
            println("Настя присвоен token = $value")
            field = value
        }

    override suspend fun getUserByToken(): User? {
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
        return result is StorageResponse.Success
    }

    override suspend fun verifyUser(email: String, code: String): User? {
        val data = storage.verifyUser(email, code)
        return if (data is StorageResponse.Success) {
            val verifyResult = data.result
            token = verifyResult?.token
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

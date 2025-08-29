package com.cerebus.network.userData

import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*


class UserApiImpl(
    private val client: HttpClient,
) : UserApi {

    override suspend fun getUserById(id: String): UserDto {
        return client.get("$BASE_URL/$id").body()
    }

    override suspend fun registerUser(createUserData: CreateUserDto): UserDto {
        return client.post(BASE_URL) {
            contentType(ContentType.Application.Json)
            setBody(createUserData)
        }.body()
    }

    override suspend fun fillUser(userData: UserDto): UserDto {
        return client.put(BASE_URL) {
            contentType(ContentType.Application.Json)
            setBody(userData)
        }.body()
    }

    companion object {
        private const val BASE_URL = "https://example.com/api/users"
    }
}
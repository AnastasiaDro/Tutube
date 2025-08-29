package com.cerebus.network.impl

import com.cerebus.network.api.UserApi
import com.cerebus.network.userData.CreateUserDto
import com.cerebus.network.userData.UserDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

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
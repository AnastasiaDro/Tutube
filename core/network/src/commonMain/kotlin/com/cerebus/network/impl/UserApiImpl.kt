package com.cerebus.network.impl

import com.cerebus.network.HttpClientProvider
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
    clientProvider: HttpClientProvider,
) : UserApi {
    private val client: HttpClient = clientProvider.getClient()
    override suspend fun getUserByToken(id: String): UserDto {
        return client.get("$BASE_URL/$id").body()
    }

   // override suspend fun registerUser(createUserData: CreateUserDto): String {
//        return client.post(BASE_URL) {
//            contentType(ContentType.Application.Json)
//            setBody(createUserData)
//        }.body()
        //TODO not implemented()
    //}

    override suspend fun registerUser(createUserData: CreateUserDto): String {
        TODO("Not yet implemented")
    }

    override suspend fun fillUser(userData: UserDto): UserDto {
        return client.put(BASE_URL) {
            contentType(ContentType.Application.Json)
            setBody(userData)
        }.body()
    }

    override suspend fun loginUser(
        login: String,
        pass: String
    ): UserDto {
        TODO("Not yet implemented")
    }


    companion object {
        private const val BASE_URL = "http://51.250.46.61:8085/api/users"
    }
}

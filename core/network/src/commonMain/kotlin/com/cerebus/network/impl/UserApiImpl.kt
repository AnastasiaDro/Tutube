package com.cerebus.network.impl

import com.cerebus.network.HttpClientProvider
import com.cerebus.network.api.UserApi
import com.cerebus.network.userData.AuthRequest
import com.cerebus.network.userData.AuthResponse
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
import kotlinx.coroutines.delay

class UserApiImpl(
    clientProvider: HttpClientProvider,
) : UserApi {
    private val client: HttpClient = clientProvider.getClient()
    override suspend fun getUserByToken(id: String): UserDto? {
        try {
            return client.get("$BASE_URL/$id").body()
        } catch (e: Exception) {
            println(e.message)
            return null
        }
    }

   // override suspend fun registerUser(createUserData: CreateUserDto): String {
//        return client.post(BASE_URL) {
//            contentType(ContentType.Application.Json)
//            setBody(createUserData)
//        }.body()
        //TODO not implemented()
    //}

    override suspend fun registerUser(createUserData: CreateUserDto): String {
        delay(100)
        return "MY TMP TOKEN REGISTER"
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
    ): AuthResponse? {
        return try {
            client.post("$BASE_URL/auth") {
                contentType(ContentType.Application.Json)
                setBody(AuthRequest(login, pass))
            }.body()
        } catch (e: Exception) {
            println("Ошибка сети: ${e.message}")
            null
        }
    }


    companion object {
        private const val BASE_URL = "http://84.201.170.110:8085/api/users"
    }
}

package com.cerebus.network.impl

import com.cerebus.network.HttpClientProvider
import com.cerebus.network.api.UserApi
import com.cerebus.network.userData.AuthRequest
import com.cerebus.network.userData.AuthResponse
import com.cerebus.network.userData.CreateUserDto
import com.cerebus.network.userData.UserDto
import com.cerebus.utils.api.logger
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json

class UserApiImpl(
    clientProvider: HttpClientProvider,
) : UserApi {
    private val client: HttpClient = clientProvider.getClient()

    //кладу userName в api/users/кладу сюда userName и в header кладу бэрор токен
    override suspend fun getUserByToken(token: String, userName: String): UserDto? {
        try {
            return client.get("$BASE_URL/$token"){
                contentType(ContentType.Application.Json)
                header(AUTH_HEADER, "$AUTH_BEARER $token")
                setBody(mapOf("userName" to userName))
            }.body()
        } catch (e: Exception) {
            logger.w(e, "AUTH", TAG) { "error in getUser!!!" }
            return null
        }
    }


    override suspend fun registerUser(createUserData: CreateUserDto): AuthResponse? {
        return try {
            client.post("$BASE_URL/auth/register") {
                contentType(ContentType.Application.Json)
                setBody(createUserData)
            }.body()
        } catch (e: Exception) {
            logger.w(e, "AUTH", TAG) { "error in registerUser!!!" }
            return null
        }
    }


    override suspend fun fillUser(token: String, userData: UserDto): UserDto? {
        return try {
            return client.put("$BASE_URL/users") {
                contentType(ContentType.Application.Json)
                header(AUTH_HEADER, "$AUTH_BEARER $token")
                setBody(userData)
            }.body()
        } catch (e: Exception) {
            logger.w(e, "AUTH", TAG) { "fillUser with Exception!!!" }
            null
        }
    }

    override suspend fun loginUser(
        login: String,
        pass: String
    ): AuthResponse? {
        return try {
            return client.post("$BASE_URL/auth/login") {
                contentType(ContentType.Application.Json)
                setBody(AuthRequest(login, pass))
            }.body()
        } catch (e: Exception) {
            logger.w(e, "AUTH", TAG) { "error in loginUser!!!" }
            null
        }
    }


    companion object {
        //51.250.43.116

        private const val TAG = "UserApiImpl"
        private const val BASE_URL = "http://51.250.43.116:8085/api"
        private const val AUTH_HEADER = "Authorization"
        private const val AUTH_BEARER = "Bearer"
    }
}

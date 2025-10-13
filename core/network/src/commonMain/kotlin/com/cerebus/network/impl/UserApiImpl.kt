package com.cerebus.network.impl

import com.cerebus.network.HttpClientProvider
import com.cerebus.network.api.UserApi
import com.cerebus.network.userData.AuthUserDto
import com.cerebus.network.userData.GetUserDto
import com.cerebus.network.userData.RegisterUserDto
import com.cerebus.network.userData.Response
import com.cerebus.network.userData.UserDto
import com.cerebus.network.userData.VerifyUserDto
import com.cerebus.utils.api.logger
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.JsonObject

class UserApiImpl(
    clientProvider: HttpClientProvider,
) : UserApi {
    private val client: HttpClient = clientProvider.getClient()

    override suspend fun getUserByToken(token: String, getUserDto: GetUserDto): Response<UserDto>? {
        return try {
            val res: Response<UserDto> = client.get("$BASE_URL/$token"){
                contentType(ContentType.Application.Json)
                header(AUTH_HEADER, "$AUTH_BEARER $token")
                setBody(mapOf("userName" to getUserDto.userName))
            }.body()
            logger.d(moduleTag = "AUTH", tag = TAG, message = { "received UserDto = $res" } )
            res
        } catch (e: Exception) {
            logger.w(e, "AUTH", TAG) { "error in getUser!!!" }
            null
        }
    }

    override suspend fun registerUser(registerUserDto: RegisterUserDto): Response<Unit>? {
        return try {
            val res: Response<Unit> = client.post("$BASE_URL/auth/register") {
                contentType(ContentType.Application.Json)
                setBody(mapOf("userName" to registerUserDto.userName))
            }.body()
            logger.d(moduleTag = "AUTH", tag = TAG, message = { "registered user Response = $res" } )
            res
        } catch (e: Exception) {
            logger.w(e, "AUTH", TAG) { "error in registerUser!!!" }
            null
        }
    }

    override suspend fun verifyUser(verifyUserDto: VerifyUserDto): Response<UserDto>? {
        return try {
            val res: Response<UserDto> = client.post("$BASE_URL/auth/register") {
                contentType(ContentType.Application.Json)
                setBody(mapOf(
                    "email" to verifyUserDto.email,
                    "code" to verifyUserDto.code))
            }.body()
            logger.d(moduleTag = "AUTH", tag = TAG, message = { "verifyUser user Response = $res" } )
            res
        } catch (e: Exception) {
            logger.w(e, "AUTH", TAG) { "error in verifyUser!!!" }
            null
        }
    }

    override suspend fun updateUser(token: String, userData: UserDto): Response<UserDto>? {
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

    override suspend fun loginUser(authUserDto: AuthUserDto): Response<UserDto>? {
        return try {
            return client.post("$BASE_URL/auth/login") {
                contentType(ContentType.Application.Json)
                setBody(AuthUserDto(authUserDto.userName, authUserDto.password))
            }.body()
        } catch (e: Exception) {
            logger.w(e, "AUTH", TAG) { "error in loginUser!!!" }
            null
        }
    }

    companion object {
        //158.160.151.235

        private const val TAG = "UserApiImpl"
        private const val BASE_URL = "http://158.160.151.235:8085/api"
        private const val AUTH_HEADER = "Authorization"
        private const val AUTH_BEARER = "Bearer"
    }
}

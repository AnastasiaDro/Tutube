package com.cerebus.network.api

import com.cerebus.network.userData.AuthUserDto
import com.cerebus.network.userData.GetUserDto
import com.cerebus.network.userData.RegisterUserDto
import com.cerebus.network.userData.Response
import com.cerebus.network.userData.UserDto
import com.cerebus.network.userData.VerifyUserDto
import kotlinx.serialization.json.JsonObject

interface UserApi {

    /**
     * Returns user details by username.
     * Requires authentication.
     */
    suspend fun getUserByToken(token: String, getUserDto: GetUserDto): Response<UserDto>?

    /**
     * Register new user with email verification.
     * Sends verification code to email.
     */
    suspend fun registerUser(registerUserDto: RegisterUserDto): Response<Unit>?

    /**
     * Verify email with confirmation code and create user account.
     * Password will be set as the verification code.
     */
    suspend fun verifyUser(verifyUserDto: VerifyUserDto): Response<UserDto>?

    /**
     * Update user information.
     * User can only update their own profile.
     * Requires authentication.
     */
    suspend fun updateUser(token: String, userData: UserDto): Response<UserDto>?

    /**
     * Authenticate user and return JWT token
     */
    suspend fun loginUser(authUserDto: AuthUserDto): Response<UserDto>?
}
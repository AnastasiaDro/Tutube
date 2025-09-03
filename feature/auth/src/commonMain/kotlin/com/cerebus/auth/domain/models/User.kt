package com.cerebus.auth.domain.models

data class User(
    val userName: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val age: Double? = null,
    val factAge: Double? = null,
    val currentLevel: Int = 1,
    val successRate: Int? = null,
    val tryingTimes: Int? = null,
)

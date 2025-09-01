package com.cerebus.auth.domain.models

data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val age: Double,
    val factAge: Double? = null,
    val currentLevel: Int = 1,
    val successRate: Int? = null,
    val tryingTimes: Int? = null,
)

package com.cerebus.auth.domain

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: DomainError) : Result<Nothing>()
}

sealed class DomainError {
    data class Network(val message: String) : DomainError()
    data class Conflict(val message: String) : DomainError()
    data class NotFound(val message: String) : DomainError()
    data class Unknown(val message: String) : DomainError()
}

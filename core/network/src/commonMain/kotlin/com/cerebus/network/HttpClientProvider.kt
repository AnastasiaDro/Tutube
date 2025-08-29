package com.cerebus.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface HttpClientProvider {
    fun getClient(): HttpClient
}

class KtorHttpClientProvider : HttpClientProvider {
    private val client = createHttpClient()
    override fun getClient() = client
}

expect fun createHttpClient(): HttpClient

fun HttpClientConfig<*>.applyDefaultConfig() {
    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            }
        )
    }
    HttpResponseValidator {
        handleResponseExceptionWithRequest { cause, _ ->
            // например, если 4xx/5xx
            println("Error: $cause")
        }
    }
}

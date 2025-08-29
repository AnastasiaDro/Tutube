package com.cerebus.tutube.di.core

import com.cerebus.network.HttpClientProvider
import com.cerebus.network.KtorHttpClientProvider
import org.koin.dsl.module

val networkModule = module {
    single<HttpClientProvider> { KtorHttpClientProvider() }
}
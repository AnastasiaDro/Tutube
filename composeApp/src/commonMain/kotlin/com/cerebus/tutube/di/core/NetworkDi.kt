package com.cerebus.tutube.di.core

import com.cerebus.network.HttpClientProvider
import com.cerebus.network.KtorHttpClientProvider
import com.cerebus.network.api.UserApi
import com.cerebus.network.impl.UserApiImpl
import org.koin.dsl.module

val networkModule = module {
    single<HttpClientProvider> {
        KtorHttpClientProvider()
    }
    single<UserApi> {
        UserApiImpl(clientProvider = get())
    }
}
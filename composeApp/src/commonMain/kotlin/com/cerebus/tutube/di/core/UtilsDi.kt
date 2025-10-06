package com.cerebus.tutube.di.core

import com.cerebus.utils.api.Logger
import com.cerebus.utils.impl.LoggerImpl
import org.koin.dsl.module

val utilsModule = module {
    single<Logger> {
        LoggerImpl()
    }
}
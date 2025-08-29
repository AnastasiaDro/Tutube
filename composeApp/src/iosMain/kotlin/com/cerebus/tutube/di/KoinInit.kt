package com.cerebus.tutube.di
import org.koin.core.context.startKoin
import org.koin.core.module.Module

actual fun initKoin(modules: List<Module>) {
    startKoin {
        modules(*modules.toTypedArray())
    }
}
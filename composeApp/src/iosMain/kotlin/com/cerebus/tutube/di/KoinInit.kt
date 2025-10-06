package com.cerebus.tutube.di

import com.cerebus.tutube.di.feature.iosAuthModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module

val iosModules = listOf(iosAuthModule)
actual fun initKoin(modules: List<Module>) {
    startKoin {
        modules(*(modules + iosModules).toTypedArray())
    }
}
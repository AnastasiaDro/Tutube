package com.cerebus.tutube.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.cerebus.tutube.MyApp
import org.koin.core.module.Module

actual fun initKoin(modules: List<Module>) {
    startKoin {
        androidContext(MyApp.instance) // нужен Application контекст
        modules(*modules.toTypedArray())
    }
}
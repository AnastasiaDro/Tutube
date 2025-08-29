package com.cerebus.tutube.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.cerebus.tutube.Appl
import org.koin.core.module.Module

actual fun initKoin(modules: List<Module>) {
    startKoin {
        androidContext(Appl.instance) // нужен Application контекст
        modules(*modules.toTypedArray())
    }
}
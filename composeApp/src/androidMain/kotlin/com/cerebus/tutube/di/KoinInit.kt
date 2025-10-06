package com.cerebus.tutube.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.cerebus.tutube.MyApp
import com.cerebus.tutube.di.feature.androidAuthModule
import org.koin.core.module.Module


val androidModules = listOf(androidAuthModule)
actual fun initKoin(modules: List<Module>) {
    startKoin {
        androidContext(MyApp.instance) // нужен Application контекст
        modules(*(modules + androidModules).toTypedArray())
    }
}
package com.cerebus.tutube.di

import com.cerebus.tutube.di.core.networkModule
import com.cerebus.tutube.di.feature.authModule
import org.koin.core.module.Module


val modules = listOf(networkModule, authModule)
expect fun initKoin(modules: List<Module>)


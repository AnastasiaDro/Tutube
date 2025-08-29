package com.cerebus.tutube.di

import com.cerebus.tutube.di.core.networkModule
import org.koin.core.module.Module


val modules = listOf(networkModule)
expect fun initKoin(modules: List<Module>)


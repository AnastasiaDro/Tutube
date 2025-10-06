package com.cerebus.tutube

import android.app.Application
import com.cerebus.tutube.di.initKoin
import com.cerebus.tutube.di.modules

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(modules)
    }

    companion object Companion {
        lateinit var instance: MyApp
            private set
    }

    init {
        instance = this
    }
}
package com.cerebus.tutube.di.feature

import com.cerebus.auth.AccountManageriOSImpl
import com.cerebus.auth.creds.AccountManager
import org.koin.dsl.module

val iosAuthModule = module {

    single<AccountManager> {
        AccountManageriOSImpl()
    }
}
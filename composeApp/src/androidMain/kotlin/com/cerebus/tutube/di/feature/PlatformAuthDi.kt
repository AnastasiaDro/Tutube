package com.cerebus.tutube.di.feature

import com.cerebus.auth.AccountManagerAndroidImpl
import com.cerebus.auth.creds.AccountManager
import org.koin.dsl.module

val androidAuthModule = module {

    single<AccountManager> {
        AccountManagerAndroidImpl(get())
    }
}
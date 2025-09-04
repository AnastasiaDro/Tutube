package com.cerebus.tutube.di.feature

import android.content.Context
import com.cerebus.auth.AccountManagerAndroidImpl
import com.cerebus.auth.creds.AccountManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidAuthModule = module {

    single<Context> { androidContext() }

    single<AccountManager> {
        AccountManagerAndroidImpl(get())
    }
}
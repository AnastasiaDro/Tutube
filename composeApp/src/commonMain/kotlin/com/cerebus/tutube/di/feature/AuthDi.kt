package com.cerebus.tutube.di.feature

import com.cerebus.auth.presentation.authscreen.AuthViewModel
import  org.koin.core.module.dsl.*
import org.koin.dsl.module

val authModule = module {
    viewModel { AuthViewModel() }
}
package com.cerebus.tutube.di.feature

import com.cerebus.auth.data.UserRepositoryImpl
import com.cerebus.auth.data.storage.UserStorage
import com.cerebus.auth.data.storage.UserStorageImpl
import com.cerebus.auth.domain.repository.UserRepository
import com.cerebus.auth.domain.usecases.AuthorizeUserUseCase
import com.cerebus.auth.domain.usecases.LoadUserUseCase
import com.cerebus.auth.domain.usecases.RegisterUserUseCase
import com.cerebus.auth.presentation.authscreen.AuthScreenNavigator
import com.cerebus.auth.presentation.authscreen.AuthViewModel
import  org.koin.core.module.dsl.*
import org.koin.dsl.module

val authModule = module {
    /** Presentation **/
    /** ViewModels **/
    viewModel { (navigator: AuthScreenNavigator) ->
        AuthViewModel(
            navigator = navigator,
            authorizeUserUseCase = get(),
            registerUserUseCase = get(),
            loadUserUseCase = get(),
        )
    }

    /** Domain **/
    /** UseCases **/
    factory<AuthorizeUserUseCase> {
        AuthorizeUserUseCase(userRepository = get())
    }
    factory<RegisterUserUseCase> {
        RegisterUserUseCase(userRepository = get(), accountManager = get())
    }
    factory<LoadUserUseCase> {
        LoadUserUseCase(userRepository = get())
    }

    /** Data **/
    /** Repositories **/
    single<UserRepository> {
        UserRepositoryImpl(storage = get())
    }
    /** Storages **/
    single<UserStorage> {
        UserStorageImpl(userApi = get())
    }

}
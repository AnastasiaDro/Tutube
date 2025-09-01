package com.cerebus.auth.presentation.authscreen

interface AuthInteractions {

    fun loadUser()

    fun onBackPressed()

    fun selectLogin()

    fun selectRegister()

    fun onLogin(login: String, pass: String)

    fun onRegister(login: String, pass: String)
}
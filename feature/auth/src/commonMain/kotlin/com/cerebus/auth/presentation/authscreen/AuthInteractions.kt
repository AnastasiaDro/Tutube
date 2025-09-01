package com.cerebus.auth.presentation.authscreen

interface AuthInteractions {

    fun loadUser()

    fun onBackPressed()

    fun onLogin(login: String, pass: String)
}
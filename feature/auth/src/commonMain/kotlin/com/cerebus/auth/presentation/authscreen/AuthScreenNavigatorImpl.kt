package com.cerebus.auth.presentation.authscreen

import androidx.navigation.NavHostController

class AuthScreenNavigatorImpl(private val navController: NavHostController) : AuthScreenNavigator {
    override fun goBack() {
        navController.navigateUp()
    }

    override fun goToUserData() {
        //TODO
        println("НАСТЯ goToUserData()")
    }
}
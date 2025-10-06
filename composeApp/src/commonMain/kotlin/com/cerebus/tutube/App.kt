package com.cerebus.tutube

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cerebus.auth.presentation.authscreen.AuthScreenWrapper
import com.cerebus.feature.profile.ProfileScreenWrapper
import com.cerebus.tutube.navigation.Screens
import com.cerebus.tutube.theme.AppTheme

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TutubeAppNavigation() = AppTheme {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = Screens.HOME.route) {

        composable(Screens.HOME.route) {
            HomeScreen(onNavigateToProfile = {
                navController.navigate(Screens.AUTHORIZATION.route)
            })
        }
        composable(Screens.AUTHORIZATION.route) {
            AuthScreenWrapper(navController)
        }
        composable(Screens.FILL_USER_PROFILE.route) {
            ProfileScreenWrapper(navController)
        }
    }
}

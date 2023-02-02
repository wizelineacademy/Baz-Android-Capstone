package com.example.baz_android_capstone.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen(route = "splash_screen")
    object Principal : Screen(route = "principal_screen")
    object Description : Screen(route = "description_screen")
}

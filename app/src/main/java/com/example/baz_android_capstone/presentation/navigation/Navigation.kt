package com.example.baz_android_capstone.presentation.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.baz_android_capstone.presentation.screens.Description
import com.example.baz_android_capstone.presentation.screens.Principal
import com.example.baz_android_capstone.presentation.screens.Splash
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { 500 },
                animationSpec = tween(1300)
            ) +
                fadeIn(
                    initialAlpha = 0.0f,
                    animationSpec = tween(1300)
                )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -500 },
                animationSpec = tween(1300)
            ) +
                fadeOut(
                    targetAlpha = 0.0f,
                    animationSpec = tween(1300)
                )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -500 },
                animationSpec = tween(1300)
            ) +
                fadeIn(
                    initialAlpha = 0.0f,
                    animationSpec = tween(1300)
                )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { 500 },
                animationSpec = tween(1300)
            ) +
                fadeOut(
                    targetAlpha = 0.0f,
                    animationSpec = tween(1300)
                )
        }
    ) {
        composable(route = Screen.Splash.route) {
            Splash(navController = navController)
        }
        composable(route = Screen.Principal.route) {
            Principal(navController = navController)
        }
        composable(route = Screen.Description.route) {
            Description(navController = navController)
        }
    }
}

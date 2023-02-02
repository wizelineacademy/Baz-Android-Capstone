package com.example.baz_android_capstone.presentation.navigation

import androidx.compose.animation.* // ktlint-disable no-wildcard-imports
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.baz_android_capstone.presentation.screens.Description
import com.example.baz_android_capstone.presentation.screens.Principal
import com.example.baz_android_capstone.presentation.screens.Splash
import com.example.baz_android_capstone.presentation.viewmodels.BookViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(navController: NavHostController) {
    val viewModel: BookViewModel = viewModel()

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
            Principal(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(
            route = Screen.Description.route,
            arguments = listOf(
                navArgument(DATA_ARGUMENT_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            Description(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

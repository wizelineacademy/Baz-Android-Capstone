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
import com.example.baz_android_capstone.util.initialOffset
import com.example.baz_android_capstone.util.transitionDuration
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
                initialOffsetX = { initialOffset },
                animationSpec = tween(transitionDuration)
            ) +
                fadeIn(
                    initialAlpha = 0.0f,
                    animationSpec = tween(transitionDuration)
                )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -initialOffset },
                animationSpec = tween(transitionDuration)
            ) +
                fadeOut(
                    targetAlpha = 0.0f,
                    animationSpec = tween(transitionDuration)
                )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -initialOffset },
                animationSpec = tween(transitionDuration)
            ) +
                fadeIn(
                    initialAlpha = 0.0f,
                    animationSpec = tween(transitionDuration)
                )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { initialOffset },
                animationSpec = tween(transitionDuration)
            ) +
                fadeOut(
                    targetAlpha = 0.0f,
                    animationSpec = tween(transitionDuration)
                )
        }
    ) {
        composable(route = Screen.Splash.route) {
            Splash(navController = navController)
        }
        composable(route = Screen.Principal.route) {
            Principal(
                navController = navController,
                bookViewModel = viewModel
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

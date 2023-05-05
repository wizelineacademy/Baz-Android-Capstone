package com.example.bazandroidcourse.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bazandroidcourse.ui.compose.HomeScreen
import com.example.bazandroidcourse.ui.compose.SplashScreen
import com.example.bazandroidcourse.ui.viewmodel.BooksViewModel

@Composable
fun  AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ApplicationScreens.SPLASH.name ){
        composable(ApplicationScreens.SPLASH.name){
            SplashScreen(navController = navController)
        }
        composable(ApplicationScreens.HOME.name){
            val viewModel = hiltViewModel<BooksViewModel>()
            HomeScreen(navController = navController, viewModel)
        }
    }

}
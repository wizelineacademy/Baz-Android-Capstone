package com.example.bazandroidcourse.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bazandroidcourse.screens.DetailScreen
import com.example.bazandroidcourse.screens.HomeScreen
import com.example.bazandroidcourse.screens.SplashScreen
import com.example.bazandroidcourse.viewmodel.BooksViewModel

@Composable
fun  AppNavigation() {
    val navController = rememberNavController()
    val bookIdArgument = "bookId"
    val viewModel = hiltViewModel<BooksViewModel>()
    NavHost(navController = navController, startDestination = ApplicationScreens.SPLASH.name ){
        composable(ApplicationScreens.SPLASH.name){
            SplashScreen(navController = navController)
        }
        composable(ApplicationScreens.HOME.name){
            HomeScreen(navController = navController, viewModel)
        }
        composable(
            route = "${ApplicationScreens.DETAIL.name}/{$bookIdArgument}",
               arguments = listOf(
                   navArgument(bookIdArgument){ type = NavType.StringType}
               ),
            ){navBack ->
            navBack.arguments?.getString(bookIdArgument)?.let{
                DetailScreen(navController = navController, viewModel,it)
            }
        }
    }

}
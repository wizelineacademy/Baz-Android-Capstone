package com.example.readbitso.navroute

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.readbitso.BitsoViewmodel
import com.example.readbitso.composeviews.Detailview
import com.example.readbitso.composeviews.Loadingview
import com.example.readbitso.composeviews.Mainview

@Composable
fun MenuNav(navController: NavHostController, viewModel: BitsoViewmodel = hiltViewModel()) {
    NavHost(navController = navController, startDestination = "start") {
        composable("loading") { Loadingview() }
        composable("start") { Mainview(viewModel, navHostController = navController) }
        composable("details") { Detailview(viewModel, navController) }
    }
}

package com.example.readbitso.navroute

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.readbitso.composeViews.LoadingV
import com.example.readbitso.composeViews.Mainview
import com.example.readbitso.BitsoViewmodel
import com.example.readbitso.composeViews.Detailview

@Composable
fun MenuNav(navController: NavHostController, viewModel: BitsoViewmodel = hiltViewModel()) {
    NavHost(navController = navController, startDestination = "start") {
        composable("loading"){ LoadingV() }
        composable("start") { Mainview(viewModel,navHostController = navController) }
        composable("details"){ Detailview(viewModel,navController) }
    }
}


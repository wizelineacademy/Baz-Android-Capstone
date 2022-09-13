package com.example.capproject.navroute

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.capproject.viewmodels.BitsoViewModel
import com.example.capproject.composeviews.Detailview
import com.example.capproject.composeviews.Loading
import com.example.capproject.composeviews.Mainview

@Composable
fun MenuNav(navController: NavHostController, viewModel: BitsoViewModel = hiltViewModel()) {
    NavHost(navController = navController, startDestination = "start") {
        composable("loading"){ Loading() }
        composable("start") { Mainview(viewModel,navHostController = navController) }
        composable("details"){ Detailview(viewModel,navController) }
//        composable("item1"){}
    }
}


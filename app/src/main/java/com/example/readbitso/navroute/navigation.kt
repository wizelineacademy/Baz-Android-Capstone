package com.example.capproject.navroute

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.capproject.composeviews.Loading
import com.example.capproject.composeviews.Mainview
import com.example.readbitso.BitsoViewmodel

@Composable
fun MenuNav(navController: NavHostController, viewModel: BitsoViewmodel = hiltViewModel()) {
    NavHost(navController = navController, startDestination = "start") {
        composable("loading"){ Loading() }
        composable("start") { Mainview(viewModel,navHostController = navController) }
//        composable("details"){ Detailview(viewModel,navController) }
//        composable("item1"){}
    }
}


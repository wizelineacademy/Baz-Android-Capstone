package com.example.criptobitsoproyectwz.NavigationCompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.criptobitsoproyectwz.ui.View.CriptoScreen
import com.example.criptobitsoproyectwz.ui.View.DetallesScreen
import com.example.criptobitsoproyectwz.ui.ViewModel.ViewModelCripto

@Preview
@Composable
fun NavigationGraph(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Rutas.Home.ruta ){
        composable(route = Rutas.Home.ruta){ CriptoScreen(navController) }
        composable(
            route = Rutas.Detalle.ruta+"/{cripto}",
            arguments = listOf(navArgument("cripto"){type = NavType.StringType})){ back->
            back.arguments?.getString("cripto")?.let {
                DetallesScreen(navController, it)
            }
        }
    }
}
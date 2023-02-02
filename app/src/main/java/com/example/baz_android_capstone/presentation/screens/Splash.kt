package com.example.baz_android_capstone.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.baz_android_capstone.presentation.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun Splash(navController: NavController) {
    LaunchedEffect(key1 = Unit) {
        delay(3000)
        navController.popBackStack()
        navController.navigate(Screen.Principal.route)
    }
    Box(contentAlignment = Alignment.Center) {
        Text(
            text = "Aquí va un\nSplash Screen",
            style = MaterialTheme.typography.h4
        )
    }
}
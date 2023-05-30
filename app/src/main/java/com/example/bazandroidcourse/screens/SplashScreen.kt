package com.example.bazandroidcourse.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.bazandroidcourse.R
import com.example.bazandroidcourse.navigation.ApplicationScreens
import kotlinx.coroutines.delay

@Preview( widthDp = 400, heightDp = 700, showBackground = true)
@Composable
fun Content(){
    Surface(modifier = Modifier.fillMaxWidth()){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
        Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground) , contentDescription = "")
        }
    }
}
@Composable
fun SplashScreen ( navController: NavController){
    Content()
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate( route = ApplicationScreens.HOME.name)
    }
}
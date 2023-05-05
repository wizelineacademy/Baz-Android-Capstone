package com.example.bazandroidcourse.ui.compose

import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bazandroidcourse.ui.navigation.ApplicationScreens
import kotlinx.coroutines.delay

//@Preview( widthDp = 400, heightDp = 700, showBackground = true)
@Composable
fun SplashScreen( navController: NavController){
    Surface(modifier = Modifier){
        Text("Hello I'm the Splash")
        LaunchedEffect(Unit) {
            delay(5000)
            navController.navigate( route = ApplicationScreens.HOME.name)
        }
//        runBlocking {
//            delay(5000)
//        }

    }
}
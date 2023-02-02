package com.example.baz_android_capstone // ktlint-disable package-name

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.baz_android_capstone.presentation.navigation.Navigation
import com.example.baz_android_capstone.ui.theme.BazAndroidCapstoneTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            lateinit var navController: NavHostController
            MyApp {
                navController = rememberAnimatedNavController()
                Navigation(navController = navController)
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    BazAndroidCapstoneTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {}
}

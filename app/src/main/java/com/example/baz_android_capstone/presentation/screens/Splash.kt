package com.example.baz_android_capstone.presentation.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.baz_android_capstone.R
import com.example.baz_android_capstone.presentation.navigation.Screen
import com.example.baz_android_capstone.util.*
import kotlinx.coroutines.delay

@Composable
fun Splash(navController: NavController) {
    val scale = remember { Animatable(0f) }
    LaunchedEffect(key1 = Unit) {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = scaleAnimationDuration,
                easing = {
                    OvershootInterpolator(6f)
                        .getInterpolation(it)
                }
            )
        )
        delay(delayTransition.toLong())
        navController.popBackStack()
        navController.navigate(Screen.Principal.route)
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .padding(spacer16)
                .size(circularSurfaceSize)
                .scale(scale.value),
            shape = CircleShape,
            color = PlateColor,
            border = BorderStroke(
                width = borderStroke2,
                color = BronzeColor
            )
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.offset(y = -spacer24)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Sunny image",
                    modifier = Modifier.size(imageSize),
                    contentScale = ContentScale.Inside
                )
                Text(
                    text = "Crypto Currency",
                    style = MaterialTheme.typography.h5,
                    color = GoldColor,
                    modifier = Modifier.padding(top = spacer24)
                )
            }
        }
    }
}
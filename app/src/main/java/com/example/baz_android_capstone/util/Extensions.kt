package com.example.baz_android_capstone.util

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer

fun Modifier.cradle(): Modifier = composed {
    val transition = rememberInfiniteTransition()
    val angleRatio by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 4000
                0f at 0
                1f at 1000
                -1f at 3000
                0f at 4000
            }
        )
    )
    graphicsLayer {
        rotationZ = 20f * angleRatio
    }
}

fun Modifier.presentation(): Modifier = composed {
    val transition = rememberInfiniteTransition()
    val angleRatio by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 8000
                0f at 0
                1f at 2000
                0f at 4000
                -1f at 6000
                0f at 8000
            }
        )
    )
    graphicsLayer {
        rotationY = 30f * angleRatio
    }
}

fun Modifier.spinning(): Modifier = composed {
    val transition = rememberInfiniteTransition()
    val angleRatio by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 4000
                0f at 0
                1f at 4000
            }
        )
    )
    graphicsLayer {
        rotationY = 360f * angleRatio
    }
}

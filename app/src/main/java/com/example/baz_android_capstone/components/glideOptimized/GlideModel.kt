package com.example.baz_android_capstone.components.glideOptimized

import androidx.compose.ui.layout.ContentScale
import com.example.baz_android_capstone.R

data class GlideModel(
    val url: String,
    val placeholder: Int = R.drawable.logo,
    val contentScale: ContentScale = ContentScale.None,
    val isRoundedShape: Boolean = false
)

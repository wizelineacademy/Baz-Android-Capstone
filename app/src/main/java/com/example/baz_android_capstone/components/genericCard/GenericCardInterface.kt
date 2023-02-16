package com.example.baz_android_capstone.components.genericCard

import androidx.compose.ui.graphics.Color
import com.example.baz_android_capstone.components.glideOptimized.GlideModel

interface GenericCardInterface {
    val background: Color
    val title: String?
}

data class GenericCardPresentation(
    val glideModel: GlideModel,
    override val background: Color,
    override val title: String?,
    val onClick: () -> Unit = {}
) : GenericCardInterface

data class GenericCardDescription(
    val border: Color? = null,
    override val background: Color,
    override val title: String
) : GenericCardInterface
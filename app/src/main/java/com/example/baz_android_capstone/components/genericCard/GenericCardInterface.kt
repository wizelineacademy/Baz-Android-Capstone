package com.example.baz_android_capstone.components.genericCard

import androidx.compose.ui.graphics.Color
import com.example.baz_android_capstone.components.glideOptimized.GlideModel

interface GenericCardInterface {
    val glideModel: GlideModel
    val background: Color
    val title: String?
    val subtitle: String?
    val helper: String?
}

data class GenericCardPresentation(
    override val glideModel: GlideModel,
    override val background: Color,
    override val title: String?,
    override val subtitle: String? = null,
    override val helper: String? = null,
    val onClick: () -> Unit = {}
) : GenericCardInterface

data class GenericCardDescription(
    val border: Color? = null,
    override val glideModel: GlideModel,
    override val background: Color,
    override val title: String,
    override val subtitle: String?,
    override val helper: String?
) : GenericCardInterface
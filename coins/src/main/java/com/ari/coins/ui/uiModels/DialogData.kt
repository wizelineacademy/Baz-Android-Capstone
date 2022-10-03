package com.ari.coins.ui.uiModels

import androidx.annotation.DrawableRes

data class DialogData(
    @DrawableRes val drawableRes: Int,
    val title: String,
    val description: String,
    val isCancellable: Boolean = true
)
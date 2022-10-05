package com.ari.coins.ui.uiModels

import androidx.annotation.DrawableRes

/**
 * @author Ari Valencia
 * @file DialogData
 * @description Model for represent generic view in
 * @see com.ari.coins.ui.views.dialogs.GenericBottomSheet GenericBottomSheet
 */

data class DialogData(
    @DrawableRes val drawableRes: Int,
    val title: String,
    val description: String,
    val isCancellable: Boolean = true,
    val showBackButton: Boolean = true
)

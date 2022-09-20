package com.ari.coins.ui.models

data class ItemString (
    val itemType:  ItemType,
    val left: String,
    val right: String
)

enum class ItemType{
    TITLE,
    INFO,
    SECTION
}
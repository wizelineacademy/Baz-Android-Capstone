package com.ari.coins.ui.uiModels

data class ItemString(
    val id: String,
    val itemType: ItemType,
    val left: String,
    val right: String
)

enum class ItemType {
    TITLE,
    INFO,
    SECTION
}

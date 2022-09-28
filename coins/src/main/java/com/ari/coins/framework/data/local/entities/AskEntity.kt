package com.ari.coins.framework.data.local.entities

import com.ari.coins.data.models.AskData

data class AskEntity(
    val amount: String,
    val book: String,
    val price: String
)

fun AskData.toEntity() = AskEntity(amount, book, price)

fun AskEntity.toData() = AskData(amount, book, price)

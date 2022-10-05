package com.ari.coins.framework.data.local.entities

import com.ari.coins.data.models.BidData

data class BidEntity(
    val amount: String,
    val book: String,
    val price: String
)

fun BidData.toEntity() = BidEntity(amount, book, price)

fun BidEntity.toData() = BidData(amount, book, price)

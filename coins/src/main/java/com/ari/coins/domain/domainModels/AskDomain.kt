package com.ari.coins.domain.domainModels

import com.ari.coins.data.models.AskData

data class AskDomain(
    val amount: String,
    val book: String,
    val price: String
)

fun AskData.toDomain() = AskDomain(amount, book, price)

package com.ari.coins.domain.domainModels

import com.ari.coins.data.models.BidData

data class BidDomain(
    val amount: String,
    val book: String,
    val price: String
)

fun BidData.toDomain() = BidDomain(amount, book, price)

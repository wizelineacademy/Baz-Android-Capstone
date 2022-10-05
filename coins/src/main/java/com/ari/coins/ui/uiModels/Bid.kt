package com.ari.coins.ui.uiModels

import com.ari.coins.domain.domainModels.BidDomain

data class Bid(
    val amount: String,
    val book: String,
    val price: String
)

fun BidDomain.toUi() = Bid(amount, book, price)

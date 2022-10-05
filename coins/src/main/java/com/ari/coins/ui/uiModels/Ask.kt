package com.ari.coins.ui.uiModels

import com.ari.coins.domain.domainModels.AskDomain

data class Ask(
    val amount: String,
    val book: String,
    val price: String
)

fun AskDomain.toUi() = Ask(amount, book, price)

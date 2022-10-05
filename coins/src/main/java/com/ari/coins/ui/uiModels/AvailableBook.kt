package com.ari.coins.ui.uiModels

import com.ari.coins.domain.domainModels.AvailableBookDomain

data class AvailableBook(
    val book: String,
    val defaultChart: String,
    val fees: Fees,
    val maximumAmount: String,
    val maximumPrice: String,
    val maximumValue: String,
    val minimumAmount: String,
    val minimumPrice: String,
    val minimumValue: String,
    val tickSize: String
)

fun AvailableBookDomain.toUi() = AvailableBook(
    book = book,
    defaultChart = defaultChart,
    fees = fees.toUi(),
    maximumAmount = maximumAmount,
    maximumPrice = maximumPrice,
    maximumValue = maximumValue,
    minimumAmount = minimumAmount,
    minimumPrice = minimumPrice,
    minimumValue = minimumValue,
    tickSize = tickSize
)

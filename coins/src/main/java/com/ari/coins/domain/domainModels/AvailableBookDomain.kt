package com.ari.coins.domain.domainModels

import com.ari.coins.data.models.AvailableBookData

data class AvailableBookDomain(
    val book: String,
    val defaultChart: String,
    val fees: FeesDomain,
    val maximumAmount: String,
    val maximumPrice: String,
    val maximumValue: String,
    val minimumAmount: String,
    val minimumPrice: String,
    val minimumValue: String,
    val tickSize: String
)

fun AvailableBookData.toDomain() = AvailableBookDomain(
    book = book,
    defaultChart = default_chart,
    fees = fees.toDomain(),
    maximumAmount = maximum_amount,
    maximumPrice = maximum_price,
    maximumValue = maximum_value,
    minimumAmount = minimum_amount,
    minimumPrice = minimum_price,
    minimumValue = minimum_value,
    tickSize = tick_size
)

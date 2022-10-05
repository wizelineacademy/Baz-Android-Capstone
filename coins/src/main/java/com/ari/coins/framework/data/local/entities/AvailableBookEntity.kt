package com.ari.coins.framework.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ari.coins.data.models.AvailableBookData

@Entity(
    tableName = "available_book_table"
)
data class AvailableBookEntity(
    @PrimaryKey val book: String,
    val defaultChart: String,
    val fees: FeesEntity,
    val maximumAmount: String,
    val maximumPrice: String,
    val maximumValue: String,
    val minimumAmount: String,
    val minimumPrice: String,
    val minimumValue: String,
    val tickSize: String
)

fun AvailableBookData.toEntity() = AvailableBookEntity(
    book = book,
    defaultChart = default_chart,
    fees = fees.toEntity(),
    maximumAmount = maximum_amount,
    maximumPrice = maximum_price,
    maximumValue = maximum_value,
    minimumAmount = minimum_amount,
    minimumPrice = minimum_price,
    minimumValue = minimum_value,
    tickSize = tick_size
)

fun AvailableBookEntity.toData() = AvailableBookData(
    book = book,
    default_chart = defaultChart,
    fees = fees.toData(),
    maximum_amount = maximumAmount,
    maximum_price = maximumPrice,
    maximum_value = maximumValue,
    minimum_amount = minimumAmount,
    minimum_price = minimumPrice,
    minimum_value = minimumValue,
    tick_size = tickSize
)

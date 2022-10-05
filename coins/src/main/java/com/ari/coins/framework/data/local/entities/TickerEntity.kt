package com.ari.coins.framework.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ari.coins.data.models.TickerData

@Entity(
    tableName = "ticker_table"
)
data class TickerEntity(
    @PrimaryKey val book: String,
    val ask: String,
    val bid: String,
    val change24: String,
    val createdAt: String,
    val high: String,
    val last: String,
    val low: String,
    val volume: String
)

fun TickerData.toEntity() = TickerEntity(
    book = book,
    ask = ask,
    bid = bid,
    change24 = change_24,
    createdAt = created_at,
    high = high,
    last = last,
    low = low,
    volume = volume
)

fun TickerEntity.toData() = TickerData(
    book = book,
    ask = ask,
    bid = bid,
    change_24 = change24,
    created_at = createdAt,
    high = high,
    last = last,
    low = low,
    volume = volume
)

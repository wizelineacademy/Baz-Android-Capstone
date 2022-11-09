package com.example.cryptocurrencyapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptocurrencyapp.domain.entity.WCCTickerDTO

@Entity(tableName = "ticker_table")
data class TickerEntity (
    @PrimaryKey val book: String,
    val high: String = "",
    val low: String
)

fun TickerEntity.toWCCTickerDTO() =
    WCCTickerDTO(
        book = this.book,
        high = high,
        low = low
    )


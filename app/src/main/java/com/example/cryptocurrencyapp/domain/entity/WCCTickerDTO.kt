package com.example.cryptocurrencyapp.domain.entity

import com.example.cryptocurrencyapp.data.database.entities.TickerEntity

data class WCCTickerDTO(
    val book: String = "",
    val high: String = "",
    val low: String = ""
)

fun WCCTickerDTO.toTickerEntity() =
    TickerEntity(
        book = this.book,
        high = high,
        low = low
    )
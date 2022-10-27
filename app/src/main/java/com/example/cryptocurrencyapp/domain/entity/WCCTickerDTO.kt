package com.example.cryptocurrencyapp.domain.entity

data class WCCTickerDTO(
    val book: String = "",
    val volume: String = "",
    val high: String = "",
    val last: String = "",
    val low: String = "",
    val vwap: String = "",
    val ask: String = "",
    val bid: String = "",
    val createdAt: String = ""
)
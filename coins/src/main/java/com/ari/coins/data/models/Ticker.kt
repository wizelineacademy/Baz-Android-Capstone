package com.ari.coins.data.models

data class Ticker(
    val ask: String,
    val bid: String,
    val book: String,
    val change_24: String,
    val created_at: String,
    val high: String,
    val last: String,
    val low: String,
    val rolling_average_change: RollingAverageChange,
    val volume: String,
    val vwap: String
)
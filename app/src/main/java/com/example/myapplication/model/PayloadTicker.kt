package com.example.myapplication.model

data class PayloadTicker(
    val ask: String,
    val bid: String,
    val book: String,
    val created_at: String,
    val high: String,
    val last: String,
    val low: String,
    val volume: String,
    val vwap: String,
    val change_24: String
)
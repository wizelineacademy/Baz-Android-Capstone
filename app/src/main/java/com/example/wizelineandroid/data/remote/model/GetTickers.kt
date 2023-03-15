package com.example.wizelineandroid.data.remote.model

// Get ticker
data class GetTickers(
    val payload: GetTicker
)

data class GetTicker(
    val high: String,
    val last: String,
    val low: String
)

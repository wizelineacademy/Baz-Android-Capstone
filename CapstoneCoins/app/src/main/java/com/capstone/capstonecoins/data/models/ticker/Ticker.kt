package com.capstone.capstonecoins.data.models.ticker

data class Ticker(
    val payload: List<Payload>,
    val success: Boolean
)
package com.capstone.capstonecoins.data.models.ticker

data class Payload(
    val asks: List<Ask>,
    val bids: List<Bid>,
    val sequence: String,
    val updated_at: String
)
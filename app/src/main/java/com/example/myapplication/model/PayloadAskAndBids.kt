package com.example.myapplication.model

data class PayloadAskAndBids(
    val asks: List<Ask>,
    val bids: List<Bid>,
    val sequence: String,
    val updated_at: String
)
package com.example.baz_android_capstone.data.models.orderBook

data class Payload(
    val asks: List<Ask>,
    val bids: List<Bid>,
    val sequence: String,
    val updated_at: String
)
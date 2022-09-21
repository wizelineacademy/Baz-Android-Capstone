package com.vero.cursowizelinecriptomonedas.model

data class Payload(
    val asks: List<CryptoOrder>,
    val bids: List<CryptoOrder>,
    val sequence: String,
    val updated_at: String
)
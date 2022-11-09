package com.course.criptomonedas.data.models

data class ModelAsksBids(
    val asks: List<Ask>,
    val bids: List<Bid>,
    val updated_at: String
)
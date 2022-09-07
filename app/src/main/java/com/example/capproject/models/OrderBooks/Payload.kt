package com.example.capproject.models.OrderBooks

import com.example.capproject.models.OrderBooks.Ask
import com.example.capproject.models.OrderBooks.Bid

data class Payload(
    val asks: List<Ask>,
    val bids: List<Bid>,
    val sequence: String,
    val updated_at: String
)
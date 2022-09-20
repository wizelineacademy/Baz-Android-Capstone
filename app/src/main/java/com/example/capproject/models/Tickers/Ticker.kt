package com.example.capproject.models.Tickers

import com.example.capproject.models.Book.ErrorX

data class Ticker(
    val payload: List<Payload>,
    val success: Boolean,
    val Error: ErrorX
)
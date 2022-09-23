package com.example.readbitso.models.bitsotickers
import com.example.readbitso.models.ErrorX

data class Tickers(
    val success: Boolean,
    val payload: PayloadTickers,
    val Error: ErrorX
    )

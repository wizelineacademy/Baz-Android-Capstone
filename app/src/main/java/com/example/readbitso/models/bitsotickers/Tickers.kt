package com.example.readbitso.models.bitsotickers

import com.example.capproject.models.Tickers.PayloadTickers
import com.example.readbitso.models.ErrorX

data class Tickers(
    val payloadTickers: List<PayloadTickers>,
    val success: Boolean,
    val Error: ErrorX
    )

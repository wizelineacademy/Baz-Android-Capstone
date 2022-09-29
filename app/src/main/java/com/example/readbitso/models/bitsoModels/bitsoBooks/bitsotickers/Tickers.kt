package com.example.readbitso.models.bitsoModels.bitsoBooks.bitsotickers
import com.example.readbitso.models.bitsoModels.bitsoBooks.ErrorX

data class Tickers(
    val success: Boolean,
    val payload: PayloadTickers,
    val Error: ErrorX
)

package com.example.cryptocurrencyapp.domain.entity

import com.example.cryptocurrencyapp.data.database.entities.AskEntity
import com.example.cryptocurrencyapp.data.database.entities.BidEntity
import kotlin.concurrent.timerTask


data class WCCOrdeRDTO(
    val ask: List<WCCOrderBookDTO> = mutableListOf(),
    val bids: List<WCCOrderBookDTO> = mutableListOf(),
)
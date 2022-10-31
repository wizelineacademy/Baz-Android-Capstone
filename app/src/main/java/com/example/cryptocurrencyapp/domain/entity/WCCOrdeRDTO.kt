package com.example.cryptocurrencyapp.domain.entity


data class WCCOrdeRDTO (
    val ask: MutableList <WCCOrderBookDTO>? = mutableListOf(),
    val bids: MutableList <WCCOrderBookDTO>? = mutableListOf(),
)
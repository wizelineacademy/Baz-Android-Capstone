package com.example.cryptocurrencyapp.domain.entity


data class WCCOrdeRDTO(
    val ask: List<WCCOrderBookDTO> = mutableListOf(),
    val bids: List<WCCOrderBookDTO> = mutableListOf(),
)
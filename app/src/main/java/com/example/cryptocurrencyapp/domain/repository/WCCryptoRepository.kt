package com.example.cryptocurrencyapp.domain.repository

import com.example.cryptocurrencyapp.data.entity.response.WCCryptoAvailableResponse
import com.example.cryptocurrencyapp.domain.entity.WCCOrdeRDTO
import com.example.cryptocurrencyapp.domain.entity.WCCTickerDTO
import com.example.cryptocurrencyapp.domain.entity.WCCryptoBookDTO
import com.example.cryptocurrencyapp.utils.Resource
import retrofit2.Response

interface WCCryptoRepository {
    suspend fun getAvailableBooks(): List<WCCryptoBookDTO>
    suspend fun getTickerBook(book: String): WCCTickerDTO
    suspend fun getOrderBook(book: String): WCCOrdeRDTO
}

package com.example.cryptocurrencyapp.data.repository

import com.example.cryptocurrencyapp.data.api.CryptoApi
import com.example.cryptocurrencyapp.data.entity.response.WCCryptoAvailableResponse
import com.example.cryptocurrencyapp.domain.entity.WCCOrdeRDTO
import com.example.cryptocurrencyapp.domain.entity.WCCTickerDTO
import com.example.cryptocurrencyapp.domain.entity.WCCryptoBookDTO
import com.example.cryptocurrencyapp.domain.repository.WCCryptoRepository


class WCCryptoRepositoryImp(private val api: CryptoApi) : WCCryptoRepository {

    override suspend fun getAvailableBooks(): List<WCCryptoBookDTO> =
        api.getExchangeBooks().toListWCCryptoBookDTO().orEmpty()

    override suspend fun getTickerBook(book: String): WCCTickerDTO =
        api.getTickerBook(book = book).payload?.toBitsoTicker() ?: WCCTickerDTO()

    override suspend fun getOrderBook(book: String): WCCOrdeRDTO =
        api.getOrderBook(book = book).payload?.toOrder() ?: WCCOrdeRDTO()
}

fun WCCryptoAvailableResponse.toListWCCryptoBookDTO() =
    this.payload?.map {
        it.toBook()
    }


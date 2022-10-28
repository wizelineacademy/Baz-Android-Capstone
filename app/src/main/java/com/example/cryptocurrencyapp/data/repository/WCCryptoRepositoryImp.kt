package com.example.cryptocurrencyapp.data.repository

import com.example.cryptocurrencyapp.data.api.WCCryptoApi
import com.example.cryptocurrencyapp.data.entity.response.WCCryptoAvailableResponse
import com.example.cryptocurrencyapp.data.entity.response.WCCryptoTickerResponse
import com.example.cryptocurrencyapp.domain.entity.WCCOrdeRDTO
import com.example.cryptocurrencyapp.domain.entity.WCCTickerDTO
import com.example.cryptocurrencyapp.domain.entity.WCCryptoBookDTO
import com.example.cryptocurrencyapp.domain.repository.WCCryptoRepository

// aqui se determina se se hace el consumo de manera remota o desde el datasource

class WCCryptoRepositoryImp(private val api: WCCryptoApi) : WCCryptoRepository {
    override suspend fun getAvaliableBooks(): List<WCCryptoBookDTO> =
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


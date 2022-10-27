package com.example.cryptocurrencyapp.data.repository

import com.example.cryptocurrencyapp.data.api.WCCryptoApi
import com.example.cryptocurrencyapp.data.entity.response.WCCryptoAvailableResponse
import com.example.cryptocurrencyapp.domain.entity.WCCryptoBookDTO
import com.example.cryptocurrencyapp.domain.repository.WCCryptoRepository
// aqui se determina se se hace el consumo de manera remota o desde el datasource

class WCCryptoRepositoryImp(private val api: WCCryptoApi) : WCCryptoRepository {
    override suspend fun getAvaliableBooks(): List<WCCryptoBookDTO> =
        api.getExchangeBooks().toListWCCryptoBookDTO().orEmpty()
}
fun WCCryptoAvailableResponse.toListWCCryptoBookDTO() =
    this.payload?.map {
        it.toBook()
    }
/* suspend fun getAvilableBooks(): WCCryptoAvailableResponse =
        api.getExchangeBooks()

    suspend fun getOrderBook(book: String): WCCryptoTickerResponse =
        api.getTickerBook(book = book)

    suspend fun getTiker(book: String): WCCryptoOrderResponse =
        api.getOrderBook(book = book)*/




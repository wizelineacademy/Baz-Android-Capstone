package com.jpgl.cryptocurrencies.data


import com.jpgl.cryptocurrencies.data.model.BidsModel
import com.jpgl.cryptocurrencies.data.model.BookModel
import com.jpgl.cryptocurrencies.data.model.TickerModel
import com.jpgl.cryptocurrencies.data.webservice.CryptoService
import javax.inject.Inject

class CryptoRepository @Inject constructor(
    private val api : CryptoService,
    private val cryptoProvider: CryptoProvider
){
    //Gestionar si accedemos a network, database
    suspend fun getAllAvailableBooks(): List<BookModel> {
        val response = api.getAvailableBooks().bookData
        cryptoProvider.books = response
        return response
    }

    suspend fun getAllBids(): List<BidsModel> {
        val response = api.getOrderBooks().bidsResponse.dataBids
        cryptoProvider.bids = response
        return response
    }

    suspend fun getAllTicker(): TickerModel {
        val response = api.getTicker().dataTicker
        cryptoProvider.ticker = response
        return response
    }

}
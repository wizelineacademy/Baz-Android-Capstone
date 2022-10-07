package com.example.cryptocurrency_challenge.data.network

import com.example.cryptocurrency_challenge.data.model.Available_books_response
import com.example.cryptocurrency_challenge.data.model.InfoTickerResponse
import com.example.cryptocurrency_challenge.data.model.OrderBookResponse
import retrofit2.Response
import javax.inject.Inject

class RetrofitClientImpl @Inject constructor (private val availableBookService: ApiBitsoService) : NetworkDataSource {

    override suspend fun getAvailablebooks(): Response<Available_books_response> =
        availableBookService.getAllCurrencies()

    override suspend fun getTicker(currency_name: String?): Response<InfoTickerResponse> =
        availableBookService.getInfoTicker(currency_name!!)

    override suspend fun getOrderBook(currency_name: String?): Response<OrderBookResponse> =
        availableBookService.getOrderBook(currency_name!!)
}
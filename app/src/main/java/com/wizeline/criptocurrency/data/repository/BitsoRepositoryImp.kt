package com.wizeline.criptocurrency.data.repository

import com.wizeline.criptocurrency.data.remote.dto.BitsoApi
import com.wizeline.criptocurrency.data.remote.dto.response.AvailableBooksResponse
import com.wizeline.criptocurrency.data.remote.dto.response.OrderBookResponse
import com.wizeline.criptocurrency.data.remote.dto.response.TickerResponse
import retrofit2.Response
import javax.inject.Inject

class BitsoRepositoryImp @Inject constructor (private val api: BitsoApi) : BitsoRepository {

    override suspend fun getAvailableBooks(): Response<AvailableBooksResponse> =
        api.getAvaliableBooks()

    override suspend fun getTicker(book: String): Response<TickerResponse> =
        api.getTicker(book = book)

    override suspend fun getOrderBook(book: String): Response<OrderBookResponse> =
        api.getOrderBook(book = book)

}
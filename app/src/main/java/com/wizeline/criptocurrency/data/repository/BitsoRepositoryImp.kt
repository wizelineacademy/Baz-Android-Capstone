package com.wizeline.criptocurrency.data.repository

import com.wizeline.criptocurrency.data.remote.dto.BitsoApi
import com.wizeline.criptocurrency.data.remote.dto.response.AvailableBooksResponse
import com.wizeline.criptocurrency.data.remote.dto.response.TickerResponse
import com.wizeline.criptocurrency.domain.model.AvailableBook
import com.wizeline.criptocurrency.domain.model.OrderBook
import com.wizeline.criptocurrency.domain.model.Ticker
import com.wizeline.criptocurrency.domain.repository.BitsoRepository
import retrofit2.Response
import javax.inject.Inject

class BitsoRepositoryImp @Inject constructor (private val api: BitsoApi) : BitsoRepository {

    override suspend fun getAvailableBooks(): List<AvailableBook> {
      return  api.getAvaliableBooks().payload?.map { it.toAvailableBook()}?: listOf()
    }

    override suspend fun getTicker(book: String): Ticker {
        return api.getTicker(book = book).payload?.toTicker() ?: Ticker()
    }

    override suspend fun getOrderBook(book: String): OrderBook {
        return api.getOrderBook(book = book).toOrderBook(book=book)
    }

}
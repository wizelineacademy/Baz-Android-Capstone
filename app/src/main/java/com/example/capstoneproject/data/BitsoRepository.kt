package com.example.capstoneproject.data

import com.example.capstoneproject.data.model.availableBooks.AvailableBookModel
import com.example.capstoneproject.data.model.BitsoProvider
import com.example.capstoneproject.data.model.orderBook.OrderBookModel
import com.example.capstoneproject.data.model.ticker.TickerModel
import com.example.capstoneproject.data.network.BitsoApiClient

class BitsoRepository {

    private val api = BitsoApiClient()

    suspend fun getAllAvailableBooks(): List<AvailableBookModel> {
        val response = api.getAvailableBooks()
        BitsoProvider.availableBooks = response.filter { it.book.contains("mxn") }
        return BitsoProvider.availableBooks
    }

    suspend fun getTicker(book: String): TickerModel = api.getTicker(book)


    suspend fun getOrderBook(book: String): OrderBookModel = api.getOrderBook(book)

}
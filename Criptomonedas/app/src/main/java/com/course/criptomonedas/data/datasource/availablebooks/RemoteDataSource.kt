package com.course.criptomonedas.data.datasource.availablebooks

import com.course.criptomonedas.data.models.AvailableBooks
import com.course.criptomonedas.data.models.ModelDetails
import com.course.criptomonedas.data.models.OrderBook

interface RemoteDataSource {
    suspend fun getAvailableBooks(): AvailableBooks
    suspend fun getDetailBooks(id: String): ModelDetails
    suspend fun getOrderBook(book:String): OrderBook
}
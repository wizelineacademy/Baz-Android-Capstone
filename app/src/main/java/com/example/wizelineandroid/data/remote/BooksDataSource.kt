package com.example.wizelineandroid.data.remote

import com.example.wizelineandroid.data.model.Books
import com.example.wizelineandroid.data.model.GetTickers
import com.example.wizelineandroid.data.model.OrderBook
import com.example.wizelineandroid.repository.WebService

class BooksDataSource(private val webService: WebService) {

    //Llamada al servidor
    suspend fun getAvailablesbooks(): Books = webService.getAvailableBooks()

    suspend fun getTickerBooks(id: String): GetTickers = webService.getTickerBooks(id)

    suspend fun getOrderBooks(id: String): OrderBook = webService.getOrderBooks(id)

}
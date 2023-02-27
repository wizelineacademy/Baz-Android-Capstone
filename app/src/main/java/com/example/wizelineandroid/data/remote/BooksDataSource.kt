package com.example.wizelineandroid.data.remote

import com.example.wizelineandroid.data.remote.model.Books
import com.example.wizelineandroid.data.remote.model.GetTickers
import com.example.wizelineandroid.data.remote.model.OrderBook
import com.example.wizelineandroid.repository.WebService
import javax.inject.Inject

class BooksDataSource @Inject constructor(private val webService: WebService) {

    //Llamada al servidor
    suspend fun getAvailablesbooks(): Books = webService.getAvailableBooks()

    suspend fun getTickerBooks(id: String): GetTickers = webService.getTickerBooks(id)

    suspend fun getOrderBooks(id: String): OrderBook = webService.getOrderBooks(id)

}
package com.example.myapplication.repository

import com.example.myapplication.data.model.BookResponse
import com.example.myapplication.data.model.OrderBooksModel
import com.example.myapplication.data.model.TickerPayloadResponse
import com.example.myapplication.data.remote.BitsoDataSource

class BitsoRepositoryImpl(private  val dataSource: BitsoDataSource): BitsoRepository {

    override suspend fun getTicker(): TickerPayloadResponse = dataSource.getTicker()

    override suspend fun getOrderBook(): OrderBooksModel = dataSource.getOrderBook()

    override suspend fun getAvailableBook(): BookResponse = dataSource.getAvailableBook()

}
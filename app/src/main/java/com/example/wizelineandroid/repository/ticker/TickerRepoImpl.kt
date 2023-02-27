package com.example.wizelineandroid.repository.ticker

import com.example.wizelineandroid.data.remote.model.GetTickers
import com.example.wizelineandroid.data.remote.BooksDataSource

class TickerRepoImpl(private val dataSource: BooksDataSource): TickerRepo {
    override suspend fun getTickerBooks(id: String): GetTickers = dataSource.getTickerBooks(id)

}
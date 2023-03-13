package com.example.wizelineandroid.repository.ticker

import com.example.wizelineandroid.data.remote.BooksDataSource
import com.example.wizelineandroid.data.remote.model.GetTickers
import javax.inject.Inject

class TickerRepoImpl  @Inject constructor(private val dataSource: BooksDataSource) : TickerRepo {
    override suspend fun getTickerBooks(id: String): GetTickers = dataSource.getTickerBooks(id)
}

package com.capstone.capstonecoins.domain.api

import com.capstone.capstonecoins.data.models.ticker.tickerquery.TickerWithQuery
import com.capstone.capstonecoins.data.repository.models.Book
import com.capstone.capstonecoins.data.repository.models.BookDetail

interface DetailCoinRepository {
    suspend fun getDetailCoin(typeCoin: String): BookDetail
}
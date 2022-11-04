package com.capstone.capstonecoins.data.repository

import com.capstone.capstonecoins.data.models.ticker.tickerquery.TickerWithQuery
import com.capstone.capstonecoins.domain.api.ApiService
import com.capstone.capstonecoins.domain.api.DetailCoinRepository

class DetailCoinRepositoryImpl(private val api: ApiService) : DetailCoinRepository {
    override suspend fun getDetailCoin(typeCoin: String): TickerWithQuery =
        api.getTicker(typeCoin)

}
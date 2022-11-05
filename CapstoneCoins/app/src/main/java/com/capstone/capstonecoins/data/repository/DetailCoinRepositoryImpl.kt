package com.capstone.capstonecoins.data.repository

import com.capstone.capstonecoins.data.models.orderbook.OrderBooks
import com.capstone.capstonecoins.data.repository.models.BookDetail
import com.capstone.capstonecoins.data.utils.toDetail
import com.capstone.capstonecoins.domain.api.ApiService
import com.capstone.capstonecoins.domain.api.DetailCoinRepository

class DetailCoinRepositoryImpl(private val api: ApiService) : DetailCoinRepository {
    override suspend fun getDetailCoin(typeCoin: String): BookDetail =
        api.getTicker(typeCoin).toDetail()

    override suspend fun getBidsAsksCoin(typeCoin: String): OrderBooks =
        api.getOrderBooks(typeCoin)


}
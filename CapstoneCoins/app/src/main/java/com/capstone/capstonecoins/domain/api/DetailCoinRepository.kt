package com.capstone.capstonecoins.domain.api

import com.capstone.capstonecoins.data.models.orderbook.OrderBooks
import com.capstone.capstonecoins.data.repository.models.BookDetail

interface DetailCoinRepository {
    suspend fun getDetailCoin(typeCoin: String): BookDetail
    suspend fun getLocalDetailCoin(typeCoin: String): BookDetail
    suspend fun getBidsAsksCoin(typeCoin: String): OrderBooks
    suspend fun getLocalBidsAsksCoin(): List<OrderBooks>
    suspend fun insertLocalDetailBook(bookDetail: BookDetail)

}
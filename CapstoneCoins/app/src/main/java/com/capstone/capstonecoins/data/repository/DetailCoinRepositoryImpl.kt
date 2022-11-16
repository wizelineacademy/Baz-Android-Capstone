package com.capstone.capstonecoins.data.repository

import com.capstone.capstonecoins.data.models.orderbook.OrderBooks
import com.capstone.capstonecoins.data.repository.models.BookDetail
import com.capstone.capstonecoins.data.utils.toDetail
import com.capstone.capstonecoins.domain.api.ApiService
import com.capstone.capstonecoins.domain.api.BooksDao
import com.capstone.capstonecoins.domain.api.DetailCoinRepository
import javax.inject.Inject

class DetailCoinRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dao: BooksDao
) :
    DetailCoinRepository {
    override suspend fun getDetailCoin(typeCoin: String): BookDetail =
        api.getTicker(typeCoin).toDetail()

    override suspend fun getLocalDetailCoin(typeCoin: String): BookDetail =
        dao.getLocalDetailBooks(typeCoin)

    override suspend fun getBidsAsksCoin(typeCoin: String): OrderBooks =
        api.getOrderBooks(typeCoin)

    override suspend fun getLocalBidsAsksCoin(): List<OrderBooks> {
        TODO("Not yet implemented")
    }

    override suspend fun insertLocalDetailBook(bookDetail: BookDetail) =
        dao.insertLocalDetailBooks(bookDetail)
}
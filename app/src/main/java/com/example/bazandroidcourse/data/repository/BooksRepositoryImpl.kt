package com.example.bazandroidcourse.data.repository

import com.example.bazandroidcourse.data.datasource.local.CryptoLocalDataSourceInterface
import com.example.bazandroidcourse.data.datasource.remote.CryptoRemoteDataSourceInterface
import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.BookOrdersModel
import com.example.bazandroidcourse.data.utils.network.networkManagerUtils

class BooksRepositoryImpl(
    val localeDataSource: CryptoLocalDataSourceInterface,
    val remoteDataSource: CryptoRemoteDataSourceInterface,
): BooksRepositoryInterface {

    override suspend fun getAllBooks(): List<BookModel> {
        return if(networkManagerUtils.isOnline()) remoteDataSource.fetchAllBooks()
        else localeDataSource.getAllBooks()
    }

    override suspend fun getBookInfo(id: String): BookDetailModel {
       return if(networkManagerUtils.isOnline()) remoteDataSource.fetchBookDetail(id)
       else localeDataSource.getBookDetail(id)
    }

    override suspend fun getBookOrders(id: String): BookOrdersModel {
        return if(networkManagerUtils.isOnline()) remoteDataSource.fetchBookOrders(id)
        else localeDataSource.getBookOrder(id)
    }
}
package com.example.bazandroidcourse.data.datasource.remote

import com.example.bazandroidcourse.data.datasource.remote.api.retrofit.ApplicationAPIInterface
import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.entities.BookOrdersModel
import com.example.bazandroidcourse.data.utils.mappers.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CryptoRemoteDataSourceImpl(
  private val apiInterface: ApplicationAPIInterface
):CryptoRemoteDataSourceInterface {

    override suspend fun fetchAllBooks(): List<BookModel> {
        return withContext(Dispatchers.IO) {
            apiInterface.fetchAvailableBooks().payload.toDomain()
        }
    }

    override suspend fun fetchBookDetail(book: String): BookDetailModel {
        return withContext(Dispatchers.IO){
            apiInterface.fetchBookDetail(book).payload.toDomain()
        }
    }

    override suspend fun fetchBookOrders(book: String): BookOrdersModel {
        return withContext(Dispatchers.IO){
            apiInterface.fetchBookOrders(book).payload.toDomain()
        }
    }
}
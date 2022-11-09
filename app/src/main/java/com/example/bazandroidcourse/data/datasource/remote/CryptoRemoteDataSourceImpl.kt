package com.example.bazandroidcourse.data.datasource.remote

import com.example.bazandroidcourse.data.datasource.remote.api.response.BookResponse
import com.example.bazandroidcourse.data.datasource.remote.api.response.DetailResponse
import com.example.bazandroidcourse.data.datasource.remote.api.response.OrderPayloadResponse
import com.example.bazandroidcourse.data.datasource.remote.api.retrofit.ApplicationAPIInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CryptoRemoteDataSourceImpl(
  private val apiInterface: ApplicationAPIInterface
):CryptoRemoteDataSourceInterface {

    override suspend fun fetchAllBooks(): List<BookResponse> {
        return withContext(Dispatchers.IO) {
            apiInterface.fetchAvailableBooks().payload
        }
    }

    override suspend fun fetchBookDetail(book: String): DetailResponse {
        return withContext(Dispatchers.IO){
            apiInterface.fetchBookDetail(book).payload
        }
    }

    override suspend fun fetchBookOrders(book: String): OrderPayloadResponse {
        return withContext(Dispatchers.IO){
            apiInterface.fetchBookOrders(book).payload
        }
    }
}
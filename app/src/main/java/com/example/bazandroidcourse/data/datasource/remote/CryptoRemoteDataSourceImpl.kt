package com.example.bazandroidcourse.data.datasource.remote

import com.example.bazandroidcourse.data.datasource.remote.api.response.BookResponsePayload
import com.example.bazandroidcourse.data.datasource.remote.api.response.DetailResponse
import com.example.bazandroidcourse.data.datasource.remote.api.response.OrderPayloadResponse
import com.example.bazandroidcourse.data.datasource.remote.api.retrofit.ApplicationAPIInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CryptoRemoteDataSourceImpl @Inject constructor(
    private val apiInterface: ApplicationAPIInterface
) : CryptoRemoteDataSourceInterface {

    override suspend fun fetchAllBooks(): List<BookResponsePayload> =
        withContext(Dispatchers.IO) {
            apiInterface.fetchAvailableBooks().payload
        }

    override suspend fun fetchBookDetail(book: String): DetailResponse =
        withContext(Dispatchers.IO) {
            apiInterface.fetchBookDetail(book).payload
        }

    override suspend fun fetchBookOrders(book: String): OrderPayloadResponse =
        withContext(Dispatchers.IO) {
            apiInterface.fetchBookOrders(book).payload
        }
}
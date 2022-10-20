package com.example.bazandroidcourse.data.datasource.remote

import com.example.bazandroidcourse.data.datasource.remote.api.response.BookOrdersResponse
import com.example.bazandroidcourse.data.datasource.remote.api.response.transFormToDomain
import com.example.bazandroidcourse.data.datasource.remote.api.retrofit.ApplicationAPIInterface
import com.example.bazandroidcourse.data.datasource.remote.api.retrofit.RetrofitHelper
import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.entities.BookModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CryptoRemoteDataSourceImpl():CryptoRemoteDataSourceInterface {
    private val retrofit = RetrofitHelper.getRetrofitHelper()
    override suspend fun fetchBooks(): List<BookModel> {
        return withContext(Dispatchers.IO) {
            val bookResponse = retrofit.create(ApplicationAPIInterface::class.java).fetchAvailableBooks()
            bookResponse.let {
                it.payload.map{
                    it.transFormToDomain()
                }
            }
        }
    }

    override suspend fun fetchTickerInfo(book: String): BookDetailModel {
        TODO("Not yet implemented")
    }

    override suspend fun fetchResumeBooks(book: String): List<BookOrdersResponse.BookResume> {
        TODO("Not yet implemented")
    }
}
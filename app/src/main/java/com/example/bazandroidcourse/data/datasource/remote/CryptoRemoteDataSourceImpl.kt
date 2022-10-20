package com.example.bazandroidcourse.data.datasource.remote

import android.util.Log
import com.example.bazandroidcourse.data.datasource.remote.api.response.BookOrdersResponse
import com.example.bazandroidcourse.data.datasource.remote.api.response.transFormToDomain
import com.example.bazandroidcourse.data.datasource.remote.api.response.transformToDomain
import com.example.bazandroidcourse.data.datasource.remote.api.retrofit.ApplicationAPIInterface
import com.example.bazandroidcourse.data.datasource.remote.api.retrofit.RetrofitHelper
import com.example.bazandroidcourse.data.entities.BookDetailModel
import com.example.bazandroidcourse.data.entities.BookModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.create

class CryptoRemoteDataSourceImpl(
    private val retrofit: Retrofit
):CryptoRemoteDataSourceInterface {

    override suspend fun fetchBooks(): List<BookModel> {
        return withContext(Dispatchers.IO) {
            val bookResponse = retrofit.create(ApplicationAPIInterface::class.java).fetchAvailableBooks()
            val bodyResponse = bookResponse.body()
            Log.i("here","DAtasource response -> $bookResponse $bodyResponse" )
            bodyResponse?.let {
                it.payload.map{
                    it.transFormToDomain()
                }
            } ?: emptyList()
        }
    }

    override suspend fun fetchTickerInfo(id: String): BookDetailModel {
        return withContext(Dispatchers.IO){
            val bookDetailResponse = retrofit.create(ApplicationAPIInterface::class.java).getBookDetail(id).body()
            bookDetailResponse?.let {
                it.payload.transformToDomain()

            } ?: BookDetailModel()
        }
    }

    override suspend fun fetchResumeBooks(book: String): List<BookOrdersResponse.BookResume> {
        TODO("Not yet implemented")
    }
}
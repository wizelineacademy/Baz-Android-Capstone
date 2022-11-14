package com.example.bazandroidcourse.data.datasource.remote

import android.annotation.SuppressLint
import androidx.annotation.MainThread
import com.example.bazandroidcourse.data.datasource.remote.api.response.BookResponsePayload
import com.example.bazandroidcourse.data.datasource.remote.api.response.DetailResponse
import com.example.bazandroidcourse.data.datasource.remote.api.response.OrderPayloadResponse
import com.example.bazandroidcourse.data.datasource.remote.api.retrofit.ApplicationAPIInterface
import com.example.bazandroidcourse.data.entities.BookModel
import io.reactivex.Observer

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CryptoRemoteDataSourceImpl @Inject constructor(
    private val apiInterface: ApplicationAPIInterface
) : CryptoRemoteDataSourceInterface {



    @SuppressLint("CheckResult")
    override suspend fun fetchAllBooks(callback: (List<BookResponsePayload>)->(Unit)){
        apiInterface.fetchAvailableBooks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback(it.payload)
            })
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
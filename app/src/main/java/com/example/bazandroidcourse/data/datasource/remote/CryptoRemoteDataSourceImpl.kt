package com.example.bazandroidcourse.data.datasource.remote

import android.annotation.SuppressLint
import com.example.bazandroidcourse.data.datasource.remote.api.response.BookResponsePayload
import com.example.bazandroidcourse.data.datasource.remote.api.response.DetailResponse
import com.example.bazandroidcourse.data.datasource.remote.api.response.OrderPayloadResponse
import com.example.bazandroidcourse.data.datasource.remote.api.retrofit.ApplicationAPIInterface
import com.example.bazandroidcourse.data.di.ApplicationScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CryptoRemoteDataSourceImpl @Inject constructor(
    private val apiInterface: ApplicationAPIInterface,
    @ApplicationScope private val externalScope: CoroutineScope
) : CryptoRemoteDataSourceInterface {

    @SuppressLint("CheckResult")
    override suspend fun fetchAllBooks(callback: (List<BookResponsePayload>) -> (Unit)) {
        apiInterface.fetchAvailableBooks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback(it.payload)
            })
    }

    override suspend fun fetchBookDetail(book: String): DetailResponse =
        withContext(externalScope.coroutineContext) {
            apiInterface.fetchBookDetail(book).payload
        }

    override suspend fun fetchBookOrders(book: String): OrderPayloadResponse =
        withContext(externalScope.coroutineContext) {
            apiInterface.fetchBookOrders(book).payload
        }
}

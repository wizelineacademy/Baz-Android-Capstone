package com.ari.coins.framework.data.network.dataSources

import com.ari.coins.data.models.AvailableBookData
import com.ari.coins.data.models.OrderBookData
import com.ari.coins.data.models.ResultData
import com.ari.coins.data.models.TickerData
import com.ari.coins.data.network.CoinsRemoteDataSource
import com.ari.coins.framework.data.network.apis.CoinsApi
import com.ari.coins.framework.data.network.constants.ErrorCode
import com.ari.coins.framework.data.network.constants.ErrorMessage
import com.ari.coins.framework.data.network.executeRequest
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * @author Ari Valencia
 * @file CoinsRemoteDataSourceImpl
 * @description Implementation of CoinsRemoteDataSource, get info from Crypto API
 */

class CoinsRemoteDataSourceImpl @Inject constructor(
    private val coinsApi: CoinsApi
) : CoinsRemoteDataSource {

    /**
     * Example of request using RxJava
     */
    override suspend fun getAvailableBooks(): ResultData<List<AvailableBookData>> {
        return suspendCoroutine { continuation ->
            coinsApi
                .getAvailableBooks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response, throwable ->
                    if (throwable != null) {
                        val error: ResultData<List<AvailableBookData>> = when (throwable) {
                            is UnknownHostException -> ResultData.Error(
                                ErrorMessage.UNKNOWN_HOST_EXCEPTION,
                                ErrorCode.UNKNOWN_HOST_EXCEPTION
                            )
                            is ConnectException -> ResultData.Error(
                                ErrorMessage.CONNECTION_EXCEPTION,
                                ErrorCode.CONNECTION_EXCEPTION
                            )
                            else -> ResultData.Error(ErrorMessage.UNKNOWN, ErrorCode.UNKNOWN)
                        }
                        continuation.resume(error)
                        return@subscribe
                    }

                    if (response.success) {
                        response.payload?.let { payload ->
                            continuation.resume(ResultData.Success(payload))
                        } ?: continuation.resume(
                            ResultData.Error(
                                ErrorMessage.UNKNOWN,
                                ErrorCode.UNKNOWN
                            )
                        )
                    } else {
                        continuation.resume(
                            ResultData.Error(
                                response.error?.message ?: ErrorMessage.UNKNOWN,
                                ErrorCode.UNKNOWN_LOCAL
                            )
                        )
                    }
                }
        }
    }

    override suspend fun getTicker(book: String): ResultData<TickerData> =
        executeRequest { coinsApi.getTicker(book) }

    override suspend fun getOrderBook(book: String): ResultData<OrderBookData> =
        executeRequest { coinsApi.getOrderBook(book) }
}

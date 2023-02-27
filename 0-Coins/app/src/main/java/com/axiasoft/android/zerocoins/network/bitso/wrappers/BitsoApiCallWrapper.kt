package com.axiasoft.android.zerocoins.network.bitso.wrappers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException

object BitsoApiCallWrapper {

    suspend fun <T> callBitsoApiWrap(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): BitsoApiResponseWrap<T> {
        return withContext(dispatcher) {
            try {
                BitsoApiResponseWrap.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                if (throwable is HttpException) {
                    // TODO build error Object?
                    BitsoApiResponseWrap.NetworkError()
                } else {
                    BitsoApiResponseWrap.NetworkError()
                }
            }
        }
    }
}

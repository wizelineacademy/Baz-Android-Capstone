package com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book

import com.axiasoft.android.zerocoins.common.log
import com.axiasoft.android.zerocoins.network.bitso.models.BitsoBaseResponse
import com.axiasoft.android.zerocoins.network.bitso.models.BitsoErrorResponse
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiCallWrapper
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.apis.BitsoOrderBooksApi
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.response.ExchangeOrderBookResponse
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.response.ListOrderBookResponse
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.ticker.response.TickerResponse
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers

class RemoteOrderBooksRepositoryImpl(
    private val bitsoOrderBooksApi: BitsoOrderBooksApi
): RemoteOrderBooksRepository {

    override suspend fun getBooksFromApi(): BitsoApiResponseWrap<BitsoBaseResponse<ArrayList<ExchangeOrderBookResponse>>> {
        return BitsoApiCallWrapper.callBitsoApiWrap(dispatcher = Dispatchers.IO){
            bitsoOrderBooksApi.getBooksFromApi()
        }
    }

    override suspend fun getTickerFromApi(book: String): BitsoApiResponseWrap<BitsoBaseResponse<TickerResponse>> {
        return BitsoApiCallWrapper.callBitsoApiWrap(dispatcher = Dispatchers.IO){
            bitsoOrderBooksApi.getTicketsApi(book)
        }
    }

    override suspend fun getListOrderBook(book: String): BitsoApiResponseWrap<BitsoBaseResponse<ListOrderBookResponse>> {
        return BitsoApiCallWrapper.callBitsoApiWrap(dispatcher = Dispatchers.IO){
            bitsoOrderBooksApi.getListOrderBook(book)
        }
    }

    override fun getTickerFromApiObservable(book: String): Observable<BitsoBaseResponse<TickerResponse>> {
        return try {
            log("z0", "WTF")
            Observable.just(bitsoOrderBooksApi.getTickersApi(book))
        } catch (ex: Exception){
            Observable.just(
                BitsoBaseResponse(
                    success = false, error = BitsoErrorResponse(message = ex.message?:"", 0)
                ))
        }
    }
}
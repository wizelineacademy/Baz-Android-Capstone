package com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book

import com.axiasoft.android.zerocoins.ui.features.available_books.domain.apis.BitsoOrderBooksApi
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.response.ListOrderBookResponse
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.ticker.Ticker
import com.axiasoft.android.zerocoins.network.bitso.models.BitsoBaseResponse
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiCallWrapper
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.response.ExchangeOrderBookResponse
import kotlinx.coroutines.Dispatchers

class RemoteOrderBooksRepositoryImpl: RemoteOrderBooksRepository {

    private val bitsoOrderBooksApi: BitsoOrderBooksApi by lazy { BitsoOrderBooksApi.Builder().build() }

    override suspend fun getBooksFromApi(): BitsoApiResponseWrap<BitsoBaseResponse<ArrayList<ExchangeOrderBookResponse>>> {
        return BitsoApiCallWrapper.callBitsoApiWrap(dispatcher = Dispatchers.IO){
            bitsoOrderBooksApi.getBooksFromApi()
        }
    }

    override suspend fun getTicketsFromApi(book: String): BitsoApiResponseWrap<BitsoBaseResponse<Ticker>> {
        return BitsoApiCallWrapper.callBitsoApiWrap(dispatcher = Dispatchers.IO){
            bitsoOrderBooksApi.getTicketsApi(book)
        }
    }

    override suspend fun getListOrderBook(book: String): BitsoApiResponseWrap<BitsoBaseResponse<ListOrderBookResponse>> {
        return BitsoApiCallWrapper.callBitsoApiWrap(dispatcher = Dispatchers.IO){
            bitsoOrderBooksApi.getListOrderBook(book)
        }
    }
}
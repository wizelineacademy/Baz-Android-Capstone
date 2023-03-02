package com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.repositories.orderBook

import com.axiasoft.android.zerocoins.network.bitso.models.BitsoBaseResponse
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.exchangeOrderBook.response.ExchangeOrderBookResponse
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.openOrdersBook.response.ListOrderBookResponse
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.ticker.response.TickerResponse
import io.reactivex.Observable

interface RemoteOrderBooksRepository {
    suspend fun getBooksFromApi(): BitsoApiResponseWrap<BitsoBaseResponse<ArrayList<ExchangeOrderBookResponse>>>

    fun getAvailableOrderBookRX(): Observable<BitsoBaseResponse<ArrayList<ExchangeOrderBookResponse>>>

    suspend fun getTickerFromApi(book: String): BitsoApiResponseWrap<BitsoBaseResponse<TickerResponse>>

    suspend fun getListOrderBook(book: String): BitsoApiResponseWrap<BitsoBaseResponse<ListOrderBookResponse>>

    fun getTickerRX(book: String): Observable<BitsoBaseResponse<TickerResponse>>
}

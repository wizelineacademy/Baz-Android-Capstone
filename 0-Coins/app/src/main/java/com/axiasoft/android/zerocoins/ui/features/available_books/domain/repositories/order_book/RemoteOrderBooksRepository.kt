package com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book

import com.axiasoft.android.zerocoins.network.bitso.models.BitsoBaseResponse
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.response.ExchangeOrderBookResponse
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.response.ListOrderBookResponse
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.ticker.response.TickerResponse

interface RemoteOrderBooksRepository {
    suspend fun getBooksFromApi(): BitsoApiResponseWrap<BitsoBaseResponse<ArrayList<ExchangeOrderBookResponse>>>

    suspend fun getTickerFromApi(book: String): BitsoApiResponseWrap<BitsoBaseResponse<TickerResponse>>

    suspend fun getListOrderBook(book: String): BitsoApiResponseWrap<BitsoBaseResponse<ListOrderBookResponse>>
}
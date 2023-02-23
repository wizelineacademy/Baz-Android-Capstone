package com.axiasoft.android.zerocoins.ui.features.available_books.domain.apis

import com.axiasoft.android.zerocoins.network.apis.CoinApis
import com.axiasoft.android.zerocoins.network.bitso.BitsoApiConstParams.AGGREGATE_PARAM_NAME
import com.axiasoft.android.zerocoins.network.bitso.BitsoApiConstParams.BOOK_PARAM_NAME
import com.axiasoft.android.zerocoins.network.bitso.BitsoApiPaths
import com.axiasoft.android.zerocoins.network.bitso.models.BitsoBaseResponse
import com.axiasoft.android.zerocoins.network.connections.HttpConnectionManager
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.response.ExchangeOrderBookResponse
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.response.ListOrderBookResponse
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.ticker.response.TickerResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface BitsoOrderBooksApi {

    @GET(BitsoApiPaths.AVAILABLE_EXCHANGE_ORDER_BOOKS)
    suspend fun getBooksFromApi(): BitsoBaseResponse<ArrayList<ExchangeOrderBookResponse>>

    @GET(BitsoApiPaths.AVAILABLE_EXCHANGE_ORDER_BOOKS)
    fun getAvailableOrderBooksRX(): Observable<BitsoBaseResponse<ArrayList<ExchangeOrderBookResponse>>>

    @GET(BitsoApiPaths.TICKER)
    suspend fun getTicketsApi(@Query(BOOK_PARAM_NAME) book: String): BitsoBaseResponse<TickerResponse>

    @GET(BitsoApiPaths.TICKER)
    fun getTickersRXApi(@Query(BOOK_PARAM_NAME) book: String): Observable<BitsoBaseResponse<TickerResponse>>

    @GET(BitsoApiPaths.LIST_ORDER_BOOK)
    suspend fun getListOrderBook(
        @Query(BOOK_PARAM_NAME) orderBookName: String,
        @Query(AGGREGATE_PARAM_NAME) aggregate: Boolean = true,
    ): BitsoBaseResponse<ListOrderBookResponse>


    class Builder : HttpConnectionManager<BitsoOrderBooksApi>(CoinApis.BITSO) {
        override fun build(): BitsoOrderBooksApi {
            return httpClient.create(BitsoOrderBooksApi::class.java)
        }
    }
}
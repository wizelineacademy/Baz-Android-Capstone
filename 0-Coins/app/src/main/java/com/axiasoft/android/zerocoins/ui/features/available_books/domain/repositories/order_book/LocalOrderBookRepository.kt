package com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book

import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.ExchangeOrderBook
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.Ask
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.Bids
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.ticker.Ticker

interface LocalOrderBookRepository {

    suspend fun storeAvailableExchangeOrderBooks(
        availableExchangeOrderBook: ArrayList<ExchangeOrderBook>,
    )

    suspend fun retrieveExchangeOrderBooks(): ArrayList<ExchangeOrderBook>

    fun storeTickerDBWithScope(ticker: Ticker)

    suspend fun retrieveTicker(book: String): Ticker?

    fun storeBidsAndAsks(asks: List<Ask>, bids: List<Bids>)

    suspend fun retrieveBidsAndAsks(book: String): Pair<List<Ask>, List<Bids>>
}

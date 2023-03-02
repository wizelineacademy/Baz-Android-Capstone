package com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.repositories.orderBook

import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.exchangeOrderBook.ExchangeOrderBook
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.openOrdersBook.Ask
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.openOrdersBook.Bids
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.ticker.Ticker

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

package com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.repositories.order_book

import com.axiasoft.android.zerocoins.common.log
import com.axiasoft.android.zerocoins.db.ZeroCoinAppDatabase
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.mappers.toDomain
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.mappers.toEntity
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.exchange_order_book.ExchangeOrderBook
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.open_orders_book.Ask
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.open_orders_book.Bids
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.ticker.Ticker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LocalOrderBookRepositoryImpl(
    private val db: ZeroCoinAppDatabase,
) : LocalOrderBookRepository {

    val scope = CoroutineScope(Dispatchers.IO)

    override suspend fun storeAvailableExchangeOrderBooks(
        availableExchangeOrderBook: ArrayList<ExchangeOrderBook>,
    ) {
        withContext(Dispatchers.IO) {
            try {
                db.bookDao().deleteAvailableExchangeOrderBooks()
                availableExchangeOrderBook.forEach {
                    val exchangeOrderBook = it.toEntity()
                    db.bookDao().insert(exchangeOrderBook)
                }
            } catch (ex: Exception) {
                log("z0", "error inserting data to db ${ex.message}")
            }
        }
    }

    override suspend fun retrieveExchangeOrderBooks(): ArrayList<ExchangeOrderBook> {
        return withContext(Dispatchers.IO) {
            try {
                val entities = db.bookDao().getAvailableBooks() as ArrayList
                val output = entities.map { it.toDomain() }
                output as ArrayList
            } catch (ex: Exception) {
                log("z0", "error on read db ${ex.message}")
                emptyList<ExchangeOrderBook>() as ArrayList
            }
        }
    }

    override fun storeTickerDBWithScope(ticker: Ticker) {
        scope.launch(Dispatchers.IO) {
            try {
                val tickerEntity = ticker.toEntity()
                db.tickerDao().upsertTicker(tickerEntity)
            } catch (ex: Exception) {
                log("z0", "error inserting ticker data to db ${ex.message}")
            }
        }
    }

    override suspend fun retrieveTicker(book: String): Ticker? {
        return withContext(Dispatchers.IO) {
            try {
                val tickerEntity = db.tickerDao().getBook(book)
                log("z0", "Read Ticker from db $tickerEntity")
                tickerEntity.toDomain()
            } catch (ex: Exception) {
                log("z0", "error on read db ${ex.message}")
                null
            }
        }
    }

    override fun storeBidsAndAsks(asks: List<Ask>, bids: List<Bids>) {
        scope.launch {
            val askEntities = asks.map { it.toEntity() }
            val bidsEntities = bids.map { it.toEntity() }
            askEntities.forEach {
                db.askDao().upsertAsk(it)
            }
            bidsEntities.forEach {
                db.bidDao().upsertBid(it)
            }
        }
    }

    override suspend fun retrieveBidsAndAsks(book: String): Pair<List<Ask>, List<Bids>> {
        return withContext(Dispatchers.IO) {
            try {
                val asksEntities = db.askDao().getAsks(book)
                val bidsEntities = db.bidDao().getBids(book)

                val asks = asksEntities.map { it.toDomain() }
                val bids = bidsEntities.map { it.toDomain() }
                Pair(asks, bids)
            } catch (ex: Exception) {
                Pair(emptyList(), emptyList())
            }
        }
    }
}

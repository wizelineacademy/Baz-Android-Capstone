package com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book

import com.axiasoft.android.zerocoins.common.log
import com.axiasoft.android.zerocoins.db.getDatabase
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.mappers.toDomain
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.mappers.toEntity
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.ExchangeOrderBook
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.Ask
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.Bids
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.ticker.Ticker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LocalOrderBookRepositoryImpl: LocalOrderBookRepository {

    private val db by lazy { getDatabase() }

    val scope = CoroutineScope(Dispatchers.IO)

    suspend fun storeAvailableExchangeOrderBooks(
        availableExchangeOrderBook: ArrayList<ExchangeOrderBook>
    ){
        withContext(Dispatchers.IO){
            //deletes
            db.bookDao().deleteAvailableExchangeOrderBooks()
            //then adds new data
            availableExchangeOrderBook.forEach {
                val exchangeOrderBook = it.toEntity()
                db.bookDao().insert(exchangeOrderBook)
            }
        }
    }

    suspend fun retrieveExchangeOrderBooks(): ArrayList<ExchangeOrderBook>{
        return withContext(Dispatchers.IO){
            try {
                val entities = db.bookDao().getAvailableBooks() as ArrayList
                val output = entities.map { it.toDomain() }
                log("z0", "Read From db $output")
                output as ArrayList
            } catch (ex: Exception){
                log("z0", "error on read db ${ex.message}")
                emptyList<ExchangeOrderBook>() as ArrayList
            }
        }
    }

    suspend fun retrieveTicker(book: String): Ticker?{
        return withContext(Dispatchers.IO){
            try {
                val tickerEntity = db.tickerDao().getBook(book)
                log("z0", "Read Ticker from db $tickerEntity")
                tickerEntity.toDomain()
            } catch (ex: Exception){
                log("z0", "error on read db ${ex.message}")
                null
            }
        }
    }

    fun updateTickerDBWithScope(ticker: Ticker){
        scope.launch(Dispatchers.IO) {
            val tickerEntity = ticker.toEntity()
            db.tickerDao().upsertTicker(tickerEntity)
        }
    }

    suspend fun updateTickerDB(ticker: Ticker){
        withContext(Dispatchers.Unconfined){
            val tickerEntity = ticker.toEntity()
            db.tickerDao().upsertTicker(tickerEntity)
        }
    }

    fun updateBidsAndAsks(asks: List<Ask>, bids: List<Bids>){
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
}
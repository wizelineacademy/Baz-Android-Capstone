package com.wizeline.criptocurrency.data.repository

import android.content.Context
import android.util.Log
import com.wizeline.criptocurrency.common.adapters.utilities.isInternetAvailable
import com.wizeline.criptocurrency.data.database.data_source.CryptoCurrencyLocalDataSource
import com.wizeline.criptocurrency.data.database.entities.*
import com.wizeline.criptocurrency.data.remote.dto.BitsoApi
import com.wizeline.criptocurrency.data.remote.dto.response.AvailableBooksResponse
import com.wizeline.criptocurrency.domain.model.AvailableBook
import com.wizeline.criptocurrency.domain.model.OrderBook
import com.wizeline.criptocurrency.domain.model.Ticker
import com.wizeline.criptocurrency.domain.repository.BitsoRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Single
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BitsoRepositoryImp @Inject constructor(
    private val api: BitsoApi,
    private val localDataSource: CryptoCurrencyLocalDataSource,
    @ApplicationContext private val context: Context,
) : BitsoRepository {

    // ==Available Books==
    override suspend fun getAvailableBooks(): List<AvailableBook> =
        if (isInternetAvailable(context = context)) {
            val bookList = api.getAvaliableBooks().payload?.map { it.toAvailableBook() } ?: listOf()
            updateAvailableOrderBookDatabase(bookList)
            bookList
        } else {
            Log.i("LocalDataBase", "Getting availableBooks from local database.")
            localDataSource.getAllAvailableBooksFromDatabase().toAvailableBookListFromEntity().let {
                if (it.isNotEmpty()) it else emptyList()
            }.toList()
        }

    fun updateAvailableOrderBookDatabase(bookList: List<AvailableBook>) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.getAllAvailableBooksFromDatabase().run {
                if (this.isNullOrEmpty()) {
                    Log.i("LocalDataBase", "AvailableOrderBookEntity inserted.")
                    localDataSource.insertAvailableOrderBookToDatabase(bookList.toAvailableBookEntityList())
                } else {
                    Log.i("LocalDataBase", "AvailableOrderBookEntity updated.")
                    localDataSource.updateAvailableOrderBookDatabase(bookList.toAvailableBookEntityList())
                }
            }
        }
    }

    // ==Ticker==
    override suspend fun getTicker(book: String): Ticker =
        if (isInternetAvailable(context = context)) {
            val ticker = api.getTicker(book = book).payload?.toTicker() ?: Ticker()
            updateTickerDatabase(book, ticker)
            ticker
        } else {
            Log.i("LocalDataBase", "Getting ticker from local database.")
            localDataSource.getTickerFromDatabase(book).toTickerFromEntity().let { ticker ->
                if (ticker.book.isNullOrEmpty()) Ticker()
                else ticker
            }
        }

    fun updateTickerDatabase(book: String, ticker: Ticker) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("LocalDataBase", "TickerEntity deleted.")
            localDataSource.deleteTickerDatabase(book)
            Log.i("LocalDataBase", "TickerEntity inserted.")
            localDataSource.insertTickerToDatabase(ticker.toTickerEntity())
        }
    }

    // ==Order Book==
    override suspend fun getOrderBook(book: String): OrderBook =
        if (isInternetAvailable(context)) {
            val orderBook =
                api.getOrderBook(book = book).payload?.toOrderBook(book = book) ?: OrderBook()
            updateOrderBookDatabase(book, orderBook)
            orderBook
        } else localDataSource.getOrderBookfromDatabase(book).let { orderBook ->
            if (orderBook.book.isNullOrEmpty()) OrderBook()
            else orderBook
        }

    fun updateOrderBookDatabase(book: String, orderBook: OrderBook?) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.deleteOpenOrdersFromDatabase(book)
            localDataSource.insertOpenOrdersToDatabase(
                orderBook?.bids.toBidsEntityList(),
                orderBook?.asks.toAsksEntityList()
            )
            Log.i("CriptoCurrencyDataBase", "OrderBook inserted")
        }
    }

    // AvailableBooks RxJava
    override suspend fun getAvailableBooksRxJava(): Single<AvailableBooksResponse> =
        if (isInternetAvailable(context))
            api.getAvailableBooksRxJava().let {
                Single.just(
                    it.blockingSingle().body()
                )
            }
        else Single.just(getAllAvailableOrderBookRxJavaFromDatabase())

    private fun getAllAvailableOrderBookRxJavaFromDatabase(): AvailableBooksResponse =
        localDataSource.getAllAvailableBooksFromDatabase().let { it.toAvailableBookResponse() }
}

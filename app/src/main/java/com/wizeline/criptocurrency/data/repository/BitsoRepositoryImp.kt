package com.wizeline.criptocurrency.data.repository

import android.content.Context
import com.wizeline.criptocurrency.common.adapters.utilities.isInternetAvailable
import com.wizeline.criptocurrency.data.database.data_source.CryptoCurrencyLocalDataSource
import com.wizeline.criptocurrency.data.database.entities.toAvailableBookEntityList
import com.wizeline.criptocurrency.data.database.entities.toAvailableBookListFromEntity
import com.wizeline.criptocurrency.data.database.entities.toTickerEntity
import com.wizeline.criptocurrency.data.database.entities.toTickerFromEntity
import com.wizeline.criptocurrency.data.remote.dto.BitsoApi
import com.wizeline.criptocurrency.domain.model.AvailableBook
import com.wizeline.criptocurrency.domain.model.OrderBook
import com.wizeline.criptocurrency.domain.model.Ticker
import com.wizeline.criptocurrency.domain.repository.BitsoRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BitsoRepositoryImp @Inject constructor(
    private val api: BitsoApi,
    private val localDataSource: CryptoCurrencyLocalDataSource,
    @ApplicationContext private val context: Context,
) : BitsoRepository {

    override suspend fun getAvailableBooks(): List<AvailableBook> =
        if (isInternetAvailable(context = context)) {
            val bookList = api.getAvaliableBooks().payload?.map { it.toAvailableBook() } ?: listOf()

            localDataSource.getAllAvailableBooksFromDatabase().run {
                if (this.isNullOrEmpty()) {
                    localDataSource.insertAvailableOrderBookToDatabase(bookList.toAvailableBookEntityList())
                } else {
                    localDataSource.updateAvailableOrderBookDatabase(bookList.toAvailableBookEntityList())
                }
            }

            bookList

        } else {
            localDataSource.getAllAvailableBooksFromDatabase().toAvailableBookListFromEntity().let {
                if (it.isNotEmpty()) it else emptyList()
            }.toList()
        }


    override suspend fun getTicker(book: String): Ticker =
        if (isInternetAvailable(context = context)) {
           val ticker =api.getTicker(book = book).payload?.toTicker() ?: Ticker()
            localDataSource.deleteTickerDatabase(book)
            localDataSource.insertTickerToDatabase(ticker.toTickerEntity())
            ticker
        } else {
            localDataSource.getTickerFromDatabase(book).toTickerFromEntity().let {ticker->
                if (ticker.book.isNullOrEmpty()) Ticker()
                else ticker
            }

        }


    override suspend fun getOrderBook(book: String): OrderBook =
        if (isInternetAvailable(context)){
            api.getOrderBook(book = book).toOrderBook(book = book)
        }else OrderBook()



}
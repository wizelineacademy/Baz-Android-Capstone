package com.wizeline.criptocurrency.data.database.data_source

import com.wizeline.criptocurrency.data.database.dao.CryptoCurrencyDao
import com.wizeline.criptocurrency.data.database.entities.AsksEntity
import com.wizeline.criptocurrency.data.database.entities.AvailableBookEntity
import com.wizeline.criptocurrency.data.database.entities.BidsEntity
import com.wizeline.criptocurrency.data.database.entities.TickerEntity
import javax.inject.Inject

class CryptoCurrencyLocalDataSource @Inject constructor(
    private val localDB: CryptoCurrencyDao
) {

    /**==AvailableBooks==**/
    fun getAllAvailableBooksFromDatabase(): List<AvailableBookEntity> =
        localDB.getAllAvailableBooksFromDatabase()

    fun insertAvailableOrderBookToDatabase(bookList: List<AvailableBookEntity>) =
        localDB.insertAvailableOrderBookToDatabase(bookList)

    fun updateAvailableOrderBookDatabase(bookList: List<AvailableBookEntity>) =
        localDB.updateAvailableOrderBookDatabase(bookList)

    fun deleteAllAvailableBooksDatabase() =
        localDB.deleteAllAvailableBooksDatabase()


    /**==Tickers==**/
    fun getTickerFromDatabase(book: String): TickerEntity =
        localDB.getTickerFromDatabase(book)

    fun insertTickerToDatabase(ticker: TickerEntity) =
        localDB.insertTickerToDatabase(ticker)

    fun updateTickerDatabase(ticker: TickerEntity) =
        localDB.updateTickerDatabase(ticker)

    fun deleteTickerDatabase(book: String) =
        localDB.deleteTickerDatabase(book)

    /**==OrderBooks==**/
    fun getOrderBookfromDatabase(book:String) =
        localDB.getOrderBookFromDatabase(book)

    fun insertOpenOrdersToDatabase(bidsEntityList: List<BidsEntity>, asksEntityList: List<AsksEntity>) =
        localDB.insertOrderBookOpenOrdersFromDatabase(bidsEntityList, asksEntityList)

    fun deleteOpenOrdersFromDatabase(book: String) =
        localDB.deleteOrderBookOpenOrdersFromDatabase(book)

}
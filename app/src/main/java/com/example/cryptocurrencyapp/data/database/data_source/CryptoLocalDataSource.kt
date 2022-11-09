package com.example.cryptocurrencyapp.data.database.data_source

import com.example.cryptocurrencyapp.data.database.dao.CryptoDao
import com.example.cryptocurrencyapp.data.database.dao.TickerDao
import com.example.cryptocurrencyapp.data.database.entities.AskEntity
import com.example.cryptocurrencyapp.data.database.entities.AvailableBookEntity
import com.example.cryptocurrencyapp.data.database.entities.BidEntity
import com.example.cryptocurrencyapp.data.database.entities.TickerEntity
import javax.inject.Inject

class CryptoLocalDataSource @Inject constructor(
    private val cryptpDB: CryptoDao,
    private val tickerBD: TickerDao

){

    //Available
     suspend fun getAllAvailableFromDB(): List<AvailableBookEntity> =
         cryptpDB.getAllAvailableBookDB()


    suspend fun insertAvailableBookToDB(bookList: List<AvailableBookEntity>) =
        cryptpDB.insertAvailableBooDB(bookList)

    suspend fun updateAvailableBookDB(bookList: List<AvailableBookEntity>) =
        cryptpDB.updateAvailableBookDB(bookList)


    //Ticker
    suspend fun getTickerFromDB(book : String) : TickerEntity =
        tickerBD.getickerBD(book)

    suspend fun insertTickerToDB(tickerEntity: TickerEntity) =
        tickerBD.insertTickerBD(tickerEntity)


    /*suspend fun getOrderFromDB(book: String) =
        cryptpDB.getOrderBookDB(book)

    suspend fun insertOrdertoDB(askList: List<AskEntity>,bidList: List<BidEntity>) =
        cryptpDB.insertOrderBookFromDatabase(askList,bidList)*/


    //Order
    suspend fun getOrderBookDB(book: String) =
        cryptpDB.getOrderBookDB(book)

    suspend fun insertOrderBookDB(askList: List<AskEntity>, bidList: List<BidEntity>) =
        cryptpDB.insertOrderBookFromDatabase(askList, bidList)

    suspend fun deteOrderBook(book: String) =
        cryptpDB.deleteOrderBookDB(book)
}



package com.wizeline.criptocurrency.data.database.data_source

import com.wizeline.criptocurrency.data.database.dao.CryptoCurrencyDao
import com.wizeline.criptocurrency.data.database.entities.AvailableBookEntity
import com.wizeline.criptocurrency.data.database.entities.TickerEntity
import javax.inject.Inject

class CryptoCurrencyLocalDataSource @Inject constructor(
    private val localDB: CryptoCurrencyDao
) {
    fun getAllAvailableBooksFromDatabase(): List<AvailableBookEntity> =
        localDB.getAllAvailableBooksFromDatabase()

    fun getTickerFromDatabase(book: String): TickerEntity =
        localDB.getTickerFromDatabase(book)

    fun getOrderBookfromDatabase(book:String) =
        localDB.getOrderBookFromDatabase(book)



}
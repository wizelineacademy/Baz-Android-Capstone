package com.example.cryptocurrencyapp.data.database.data_source

import com.example.cryptocurrencyapp.data.database.dao.CryptoDao
import com.example.cryptocurrencyapp.data.database.entities.AvailableBookEntity
import javax.inject.Inject

class CryptoLocalDataSource @Inject constructor(
    private val cryptpDB: CryptoDao
){

    //Available
     fun getAllAvailableFromDB(): List<AvailableBookEntity> =
         cryptpDB.getAllAvailableBookDB()

    fun insertAvailableBookToDB(bookList: List<AvailableBookEntity>) =
        cryptpDB.insertAvailableBooDB(bookList)

    fun updateAvailableBookDB(bookList: List<AvailableBookEntity>) =
        cryptpDB.updateAvailableBookDB(bookList)
}
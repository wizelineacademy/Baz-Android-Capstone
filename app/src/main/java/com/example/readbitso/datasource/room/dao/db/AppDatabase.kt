package com.example.readbitso.datasource.room.dao.db

import androidx.room.*
import com.example.readbitso.datasource.room.dao.AskBidsDao
import com.example.readbitso.datasource.room.dao.CriptocurrenciesDao
import com.example.readbitso.datasource.room.dao.TradesDao
import com.example.readbitso.datasource.room.dao.entity.AskBids
import com.example.readbitso.datasource.room.dao.entity.Currencies
import com.example.readbitso.datasource.room.dao.entity.OperationsTrades

@Database(entities = [Currencies::class, OperationsTrades::class, AskBids::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun criptoCurrenciesDao(): CriptocurrenciesDao
    abstract fun tradingBookDao(): TradesDao
    abstract fun askAndBidsDao(): AskBidsDao
}

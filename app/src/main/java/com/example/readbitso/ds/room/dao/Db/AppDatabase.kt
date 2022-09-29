package com.example.readbitso.ds.room.dao.Db

import androidx.room.*
import com.example.readbitso.ds.room.dao.AskBidsDao
import com.example.readbitso.ds.room.dao.CriptocurrenciesDao
import com.example.readbitso.ds.room.dao.TradesDao
import com.example.readbitso.ds.room.dao.entity.AskBids
import com.example.readbitso.ds.room.dao.entity.Currencies
import com.example.readbitso.ds.room.dao.entity.Operationstrades

@Database(entities = [Currencies::class, Operationstrades::class, AskBids::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tokensDao(): CriptocurrenciesDao
    abstract fun tokensDao1(): TradesDao
    abstract fun tokensDao2(): AskBidsDao
}

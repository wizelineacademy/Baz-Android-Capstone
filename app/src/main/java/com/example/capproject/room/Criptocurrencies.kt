package com.example.capproject.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface CriptocurrenciesDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(usuario: List<Currencies>)


    @Query("SELECT * FROM Currencies")
    suspend fun getAll(): List<Currencies>

}

@Dao
interface TradesDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(usuario: List<Operationstrades>)


    @Query("SELECT * FROM Operationstrades")
    suspend fun getAll(): List<Operationstrades>


}

@Database(entities = [Currencies::class,Operationstrades::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tokensDao(): CriptocurrenciesDao
    abstract fun tokensDao1(): TradesDao
}

package com.baz.cmv.criptomonedas.coins.data.remote.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.baz.cmv.criptomonedas.coins.Coins
import com.baz.cmv.criptomonedas.coins.data.remote.entities.CoinsEntity

@Dao
interface CoinsDao {
  /*  @Query("SELECT * FROM coins_table ORDER BY book" )
    suspend fun getAllCoins(): List<CoinsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE) //ignorar cualquier nota nueva que sea igual a una que ya está en la lista
    suspend fun insertCoins(coins : List<CoinsEntity>  )

    @Query("DELETE FROM coins_table")
    suspend fun deleteAllCoins()*/



}






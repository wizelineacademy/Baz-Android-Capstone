package com.axiasoft.android.zerocoins.db.bitso.dao

import android.database.sqlite.SQLiteConstraintException
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.ticker.Ticker
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.ticker.entity.TickerEntity

@Dao
interface TickerDao {
    @Insert
    fun insert(tickerEntity: TickerEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTicker(ticker: TickerEntity)

    suspend fun upsertTicker(ticker: TickerEntity) {
        try {
            insert(ticker)
        }
        catch (e: SQLiteConstraintException) {
            updateTicker(ticker)
        }
    }
}
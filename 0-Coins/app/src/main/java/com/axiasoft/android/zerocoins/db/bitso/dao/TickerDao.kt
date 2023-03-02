package com.axiasoft.android.zerocoins.db.bitso.dao

import android.database.sqlite.SQLiteConstraintException
import androidx.room.*
import com.axiasoft.android.zerocoins.db.TICKER_TB_NAME
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.ticker.entity.TickerEntity

@Dao
interface TickerDao {
    @Insert
    fun insert(tickerEntity: TickerEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTicker(ticker: TickerEntity)

    suspend fun upsertTicker(ticker: TickerEntity) {
        try {
            insert(ticker)
        } catch (e: SQLiteConstraintException) {
            updateTicker(ticker)
        }
    }

    @Query("SELECT * FROM $TICKER_TB_NAME WHERE book = :book")
    fun getBook(book: String): TickerEntity
}

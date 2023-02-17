package com.axiasoft.android.zerocoins.db.bitso.dao

import android.database.sqlite.SQLiteConstraintException
import androidx.room.*
import com.axiasoft.android.zerocoins.db.ASK_TB_NAME
import com.axiasoft.android.zerocoins.db.TICKER_TB_NAME
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.entity.AskEntity
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.ticker.entity.TickerEntity

@Dao
interface AskDao {
    @Insert
    fun insert(askEntity: AskEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAsk(askEntity: AskEntity)

    suspend fun upsertAsk(askEntity: AskEntity) {
        try {
            insert(askEntity)
        }
        catch (e: SQLiteConstraintException) {
            updateAsk(askEntity)
        }
    }

    @Query("SELECT * FROM $ASK_TB_NAME WHERE book = :book")
    fun getAsks(book: String): List<AskEntity>

    @Query("Select * From $ASK_TB_NAME")
    fun getAllAsk(): List<AskEntity>

    @Query("Delete From $ASK_TB_NAME")
    fun deleteAllAsk()
}
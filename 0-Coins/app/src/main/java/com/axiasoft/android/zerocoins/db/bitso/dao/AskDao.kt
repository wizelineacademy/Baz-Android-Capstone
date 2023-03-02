package com.axiasoft.android.zerocoins.db.bitso.dao

import android.database.sqlite.SQLiteConstraintException
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.axiasoft.android.zerocoins.db.ASK_TB_NAME
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.openOrdersBook.entity.AskEntity

@Dao
interface AskDao {
    @Insert
    fun insert(askEntity: AskEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAsk(askEntity: AskEntity)

    suspend fun upsertAsk(askEntity: AskEntity) {
        try {
            insert(askEntity)
        } catch (e: SQLiteConstraintException) {
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

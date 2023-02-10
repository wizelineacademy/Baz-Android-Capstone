package com.axiasoft.android.zerocoins.db.bitso.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.axiasoft.android.zerocoins.db.ASK_TB_NAME
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.entity.AskEntity

@Dao
interface AskDao {
    @Insert
    fun insert(askEntity: AskEntity)

    @Query("Select * From $ASK_TB_NAME")
    fun getAllAsk(): List<AskEntity>

    @Query("Delete From $ASK_TB_NAME")
    fun deleteAllAsk()
}
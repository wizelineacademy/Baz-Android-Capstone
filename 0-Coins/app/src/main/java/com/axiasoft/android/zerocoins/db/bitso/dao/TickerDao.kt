package com.axiasoft.android.zerocoins.db.bitso.dao

import androidx.room.Dao
import androidx.room.Insert
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.ticker.entity.TickerEntity

@Dao
interface TickerDao {
    @Insert
    fun insert(tickerEntity: TickerEntity)
}
package com.axiasoft.android.zerocoins.db.bitso.dao

import androidx.room.Dao
import androidx.room.Insert
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.entity.BidsEntity

@Dao
interface BidsDao {
    @Insert
    fun insert(bidsEntity: BidsEntity)
}
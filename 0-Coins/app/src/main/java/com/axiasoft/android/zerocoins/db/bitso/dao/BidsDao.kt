package com.axiasoft.android.zerocoins.db.bitso.dao

import android.database.sqlite.SQLiteConstraintException
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.entity.BidsEntity
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.ticker.entity.TickerEntity

@Dao
interface BidsDao {
    @Insert
    fun insert(bidsEntity: BidsEntity)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateBid(bid: BidsEntity)


    suspend fun upsertBid(bid: BidsEntity) {
        try {
            insert(bid)
        }
        catch (e: SQLiteConstraintException) {
            updateBid(bid)
        }
    }
}
package com.axiasoft.android.zerocoins.db.bitso.dao

import android.database.sqlite.SQLiteConstraintException
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.axiasoft.android.zerocoins.db.BIDS_TB_NAME
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.openOrdersBook.entity.BidsEntity

@Dao
interface BidsDao {
    @Insert
    fun insert(bidsEntity: BidsEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateBid(bid: BidsEntity)

    suspend fun upsertBid(bid: BidsEntity) {
        try {
            insert(bid)
        } catch (e: SQLiteConstraintException) {
            updateBid(bid)
        }
    }

    @Query("SELECT * FROM $BIDS_TB_NAME WHERE book = :book")
    fun getBids(book: String): List<BidsEntity>
}

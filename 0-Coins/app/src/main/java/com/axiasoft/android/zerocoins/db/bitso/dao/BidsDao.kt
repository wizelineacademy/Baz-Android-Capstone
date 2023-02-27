package com.axiasoft.android.zerocoins.db.bitso.dao

import android.database.sqlite.SQLiteConstraintException
import androidx.room.*
import com.axiasoft.android.zerocoins.db.BIDS_TB_NAME
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.entity.BidsEntity

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

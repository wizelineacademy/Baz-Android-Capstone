package com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.axiasoft.android.zerocoins.common.emptyString
import com.axiasoft.android.zerocoins.db.BIDS_TB_NAME


@Entity(tableName = BIDS_TB_NAME, primaryKeys = ["book", "price", "amount"])
data class BidsEntity(
    @ColumnInfo("book" ) var book          : String = emptyString(),
    @ColumnInfo("price" ) var price         : String = emptyString(),
    @ColumnInfo("amount") var amount        : String = emptyString(),
)
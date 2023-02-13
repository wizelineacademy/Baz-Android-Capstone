package com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.axiasoft.android.zerocoins.db.ASK_TB_NAME

@Entity(tableName = ASK_TB_NAME, primaryKeys = ["book", "price", "amount"])
data class AskEntity(
    @ColumnInfo("book" ) var book          : String = "",
    @ColumnInfo("price" ) var price         : String = "",
    @ColumnInfo("amount") var amount        : String = "",
)
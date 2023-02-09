package com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.axiasoft.android.zerocoins.db.ASK_TB_NAME

@Entity(tableName = ASK_TB_NAME)
data class AskEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("book" ) var book          : String,

    @ColumnInfo("price" ) var price         : String? = null,
    @ColumnInfo("amount") var amount        : String? = null,
)
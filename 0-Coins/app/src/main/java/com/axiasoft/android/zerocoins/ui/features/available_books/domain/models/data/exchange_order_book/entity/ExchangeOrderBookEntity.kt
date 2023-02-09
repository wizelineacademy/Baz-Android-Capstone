package com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.entity

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_order_books")
data class ExchangeOrderBookEntity (

    @PrimaryKey(autoGenerate = false )
    @ColumnInfo(name = "book") var book          : String,

    @ColumnInfo("minimum_amount" ) var minimumAmount : String? = null,
    @ColumnInfo("maximum_amount" ) var maximumAmount : String? = null,
    @ColumnInfo("minimum_price"  ) var minimumPrice  : String? = null,
    @ColumnInfo("maximum_price"  ) var maximumPrice  : String? = null,
    @ColumnInfo("minimum_value"  ) var minimumValue  : String? = null,
    @ColumnInfo("maximum_value"  ) var maximumValue  : String? = null
)
package com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.ticker.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.axiasoft.android.zerocoins.db.TICKER_TB_NAME

@Entity(tableName = TICKER_TB_NAME)
data class TickerEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("book")
    var book: String,
    @ColumnInfo("high") var high: String? = null,
    @ColumnInfo("last") var last: String? = null,
    @ColumnInfo("created_at") var createdAt: String? = null,
    @ColumnInfo("volume") var volume: String? = null,
    @ColumnInfo("vwap") var vwap: String? = null,
    @ColumnInfo("low") var low: String? = null,
    @ColumnInfo("ask") var ask: String? = null,
    @ColumnInfo("bid") var bid: String? = null,
    @ColumnInfo("change_24") var change24: String? = null
    // @ColumnInfo("rolling_average_change" ) var rollingAverageChange : HashMap<String, String>? = null
)

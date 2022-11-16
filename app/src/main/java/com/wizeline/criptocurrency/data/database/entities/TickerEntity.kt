package com.wizeline.criptocurrency.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wizeline.criptocurrency.domain.model.Ticker

@Entity(tableName = "ticker_table")
data class TickerEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "book") var book: String? = null,
    @ColumnInfo(name = "high") var high: String? = null,
    @ColumnInfo(name = "last") var last: String? = null,
    @ColumnInfo(name = "low") var low: String? = null
)

fun Ticker.toTickerEntity() = TickerEntity(book = book, high = high, last = last, low = low)

fun TickerEntity?.toTickerFromEntity(): Ticker = Ticker(book = this?.book, high = this?.high, last = this?.last, low = this?.low)

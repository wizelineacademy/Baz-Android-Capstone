package com.jpgl.cryptocurrencies.domain.model

import androidx.room.ColumnInfo
import com.jpgl.cryptocurrencies.data.database.entities.BookEntity
import com.jpgl.cryptocurrencies.data.database.entities.TickerEntity
import com.jpgl.cryptocurrencies.data.model.BookModel
import com.jpgl.cryptocurrencies.data.model.TickerModel

data class TickerModelDomain(
    var nameBid: String,
    val high: String,
    val last: String,
    val volume: String,
    val vwap: String,
    val low: String,
    val ask: String,
    val createdAt: String,
)

fun TickerModel.toDomain() = TickerModelDomain(nameBid, high, last, volume, vwap, low, ask, createdAt)
fun TickerEntity.toDomain() = TickerModelDomain(nameBid, high, last, volume, vwap, low, ask, createdAt)
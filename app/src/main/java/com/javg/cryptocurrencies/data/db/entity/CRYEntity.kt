package com.javg.cryptocurrencies.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.javg.cryptocurrencies.data.db.remote.CRYAppDatabase

@Entity(tableName = CRYAppDatabase.BOOK_TABLE)
data class CRYBookEntity(
    @PrimaryKey
    @ColumnInfo(name = "book") val book: String,
    @ColumnInfo(name = "minimumPrice") val minimumPrice: String,
    @ColumnInfo(name = "maximumPrice") val maximumPrice: String
)

@Entity(tableName = CRYAppDatabase.DETAIL_BOOK_TABLE)
data class CRYDetailBookEntity(
    @PrimaryKey
    @ColumnInfo("book") val book: String,
    @ColumnInfo("high") val high: String,
    @ColumnInfo("last") val last: String,
    @ColumnInfo("low") val low: String,
    @ColumnInfo("askList") val askList: String,
    @ColumnInfo("bidsList") val bidsList: String
)
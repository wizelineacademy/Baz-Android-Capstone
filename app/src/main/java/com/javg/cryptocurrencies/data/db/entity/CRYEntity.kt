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
package com.example.cryptocurrencyapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "available_table")
data class AvailableBookEntity(

    @PrimaryKey val book: String = "",
    val minPrice: String = "",
    val maxPrice: String = "",
    val logo: Int,
)
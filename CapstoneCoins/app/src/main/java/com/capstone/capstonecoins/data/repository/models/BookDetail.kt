package com.capstone.capstonecoins.data.repository.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookDetail(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "book") val book: String,
    @ColumnInfo(name = "high") val high: String,
    @ColumnInfo(name = "last") val last: String,
    @ColumnInfo(name = "low") val low: String,
)

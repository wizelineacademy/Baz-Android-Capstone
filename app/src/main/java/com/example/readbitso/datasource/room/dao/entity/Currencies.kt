package com.example.readbitso.datasource.room.dao.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Currencies(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "Name") val book: String,
    @ColumnInfo(name = "Min") val maximumPrice: String,
    @ColumnInfo(name = "Max") val minimumPrice: String
)

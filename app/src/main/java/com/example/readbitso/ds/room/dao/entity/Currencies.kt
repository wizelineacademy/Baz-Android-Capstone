package com.example.readbitso.ds.room.dao.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Currencies(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "name") val book: String,
    @ColumnInfo(name = "min") val maximum_price: String,
    @ColumnInfo(name = "max") val minimum_price: String
)

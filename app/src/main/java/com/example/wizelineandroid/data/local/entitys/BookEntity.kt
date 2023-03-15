package com.example.wizelineandroid.data.local.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class BookEntity(
    @PrimaryKey()
    val id: String = "",
    @ColumnInfo(name = "name")
    val itemName: String = "",
    @ColumnInfo(name = "minimum price")
    val minimum_price: String = "",
    @ColumnInfo(name = "maximum price")
    val maximum_price: String = ""
)

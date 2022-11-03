package com.example.bazandroidcourse.data.datasource.local.database.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_details")
data class BookDetailEntity(
    @PrimaryKey @ColumnInfo(name = "book_id")
    val book    : String,
    val volume  : String,
    val high    : String,
    val last    : String,
    val low     : String,
)
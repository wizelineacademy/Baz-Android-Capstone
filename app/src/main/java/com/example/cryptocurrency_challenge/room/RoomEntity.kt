package com.example.cryptocurrency_challenge.room

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/************** Ticker Entity ************************/

@Entity(tableName = "ticker_entity")
data class TickerEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int =-1,

    @ColumnInfo(name = "book")
    var book: String = "book",

    @ColumnInfo(name = "high")
    var high: String = "high",

    @ColumnInfo(name = "last")
    @NonNull
    var last: String = "last",

    @ColumnInfo(name = "low")
    @NonNull
    var low: String = "low",
)



package com.example.readbitso.datasource.room.dao.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AskBids(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "Ask") val ask: String?,
    @ColumnInfo(name = "Bid") val bid: String?,
    @ColumnInfo(name = "Book") val book: String?
)

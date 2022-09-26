package com.example.readbitso.ds.room.dao.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AskBids(
    @PrimaryKey(autoGenerate = false)  val id: Int,
    @ColumnInfo(name = "Ask") val Ask: String?,
    @ColumnInfo(name = "Bid") val Bid: String?,
    @ColumnInfo(name = "Book") val Book: String?
)
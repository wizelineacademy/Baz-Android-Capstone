package com.example.readbitso.ds.room.dao.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AskBids(
    @PrimaryKey(autoGenerate = false)  val uid: Int,
    @ColumnInfo(name = "Pair") val pair: String?,
    @ColumnInfo(name = "amount") val Amount: String?,
    @ColumnInfo(name = "Type") val Type: String?,
    @ColumnInfo(name = "Price") val Price: String?
)
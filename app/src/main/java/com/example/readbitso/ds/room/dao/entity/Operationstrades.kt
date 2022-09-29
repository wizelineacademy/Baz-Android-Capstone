package com.example.readbitso.ds.room.dao.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Operationstrades(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "Pair") val pair: String?,
    @ColumnInfo(name = "Amount") val Amount: String?,
    @ColumnInfo(name = "Type") val Type: String?,
    @ColumnInfo(name = "Price") val Price: String?
)

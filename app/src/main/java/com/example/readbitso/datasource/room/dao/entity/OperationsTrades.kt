package com.example.readbitso.datasource.room.dao.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OperationsTrades(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "Pair") val pair: String?,
    @ColumnInfo(name = "Amount") val amount: String?,
    @ColumnInfo(name = "Type") val type: String?,
    @ColumnInfo(name = "Price") val price: String?
)

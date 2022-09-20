package com.example.capproject.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Currencies(
    @PrimaryKey (autoGenerate = false)  val uid: Int,
    @ColumnInfo(name = "name") val Name: String?,
    @ColumnInfo(name = "min") val minvalue: String?,
    @ColumnInfo(name = "max") val maxvalue: String?
)

@Entity
data class Operationstrades(
    @PrimaryKey (autoGenerate = false)  val uid: Int,
    @ColumnInfo(name = "Pair") val pair: String?,
    @ColumnInfo(name = "amount") val Amount: String?,
    @ColumnInfo(name = "Type") val Type: String?,
    @ColumnInfo(name = "Price") val Price: String?
)
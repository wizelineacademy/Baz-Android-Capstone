package com.example.readbitso.ds.room.dao.entity

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

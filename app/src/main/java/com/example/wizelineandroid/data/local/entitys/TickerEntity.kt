package com.example.wizelineandroid.data.local.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tickers")
data class TickerEntity(
    @PrimaryKey()
    val id: String = "",
    val fecha: String = "",
    val high: String = "",
    val last: String = "",
    val low: String = ""
)

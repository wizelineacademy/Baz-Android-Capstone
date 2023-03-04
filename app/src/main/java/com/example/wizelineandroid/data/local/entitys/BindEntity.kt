package com.example.wizelineandroid.data.local.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bids")
data class BidsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val book: String = "",
    val amount: String = "",
    val price: String = ""
)

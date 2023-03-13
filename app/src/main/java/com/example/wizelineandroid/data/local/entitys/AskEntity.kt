package com.example.wizelineandroid.data.local.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ask")
data class AskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val book: String = "",
    val price: String = "",
    val amount: String = ""
)

package com.example.wizelineandroid.data.local.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ask")
data class AskEntity(
    @PrimaryKey()
    val id: String = "",
    val book: String = "",
    val amount: String = "",
    val price: String = ""
)
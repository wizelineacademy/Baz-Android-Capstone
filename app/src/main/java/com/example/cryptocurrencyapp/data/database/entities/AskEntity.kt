package com.example.cryptocurrencyapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptocurrencyapp.domain.entity.WCCOrderBookDTO

@Entity(tableName = "ask_table")
data class AskEntity(
    @PrimaryKey
    val book: String,
    val id: Int = 0,
    val price: String = "",
    val amount: String = "",
    val type: String = ""
)

fun AskEntity.toWCCOrderBookDTO() =
    WCCOrderBookDTO(
        book = book,
        price = price,
        amount = amount,
        type = type
    )
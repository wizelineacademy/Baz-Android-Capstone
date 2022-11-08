package com.example.cryptocurrencyapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptocurrencyapp.domain.entity.WCCOrderBookDTO

@Entity(tableName = "bid_table")
data class BidEntity(
    @PrimaryKey  val book: String,
    val id: Int = 0,
    val price: String = "",
    val amount: String = "",
    val type: String = ""
)

fun BidEntity.toWCCOrderBookDTO() =
    WCCOrderBookDTO(
        book = book,
        price = price,
        amount = amount,
        type = type
    )


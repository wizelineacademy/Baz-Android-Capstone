package com.example.cryptocurrencyapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptocurrencyapp.domain.entity.WCCOrderBookDTO

@Entity(tableName = "ask_table")
data class AskEntity(
    @PrimaryKey (autoGenerate = true)
    val id:Int = 0,
    val book: String,
    val price: String = "",
    val amount: String = "",
    val type: String = ""
)

fun AskEntity.toWCCOrderBookDTO() =
    WCCOrderBookDTO(
        book = this.book,
        price = this.price,
        amount = this.amount,
        type = this.type
    )


fun List<WCCOrderBookDTO>?.toAskEntityList() = mutableListOf<AskEntity>().apply {
    this@toAskEntityList?.forEach {
        this.add(
            AskEntity(book = it.book, price = it.price, amount = it.amount)
        )
    }
}
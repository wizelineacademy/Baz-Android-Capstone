package com.example.bazandroidcourse.data.datasource.local.database.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_orders")
data class BookOrderEntity (
    @PrimaryKey @ColumnInfo(name = "book_id")
    val book: String,
    val price: String,
    val amount: String,
    val type: Int
 )


enum class BookOrderTypes(val id:Int){
    BID(1),
    ASK(2)
}
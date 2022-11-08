package com.example.cryptocurrencyapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "order_table")
data class OrderBookEntity (
    @PrimaryKey val book: String,
    //val
        )
package com.example.capstone_project.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.capstone_project.data.network.entities.model.Bid
import com.example.capstone_project.domain.model.BidDomain

@Entity(tableName = "bid")
data class BidsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idbid")
    val idBid: Int = 0,
    @ColumnInfo(name = "book") val book: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "amount") val amount: String
)

fun BidDomain.toDatabase() =
    BidsEntity(
        book = book,
        price = price,
        amount = amount
    )
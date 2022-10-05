package com.ari.coins.framework.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ari.coins.data.models.AskData
import com.ari.coins.data.models.BidData
import com.ari.coins.data.models.OrderBookData

@Entity(
    tableName = "order_book_table"
)
data class OrderBookEntity(
    @PrimaryKey val book: String,
    val asks: List<AskEntity>,
    val bids: List<BidEntity>,
    val sequence: String,
    val updatedAt: String
)

fun OrderBookData.toEntity(book: String) = OrderBookEntity(
    book = book,
    asks = asks.map(AskData::toEntity),
    bids = bids.map(BidData::toEntity),
    sequence = sequence,
    updatedAt = updated_at
)

fun OrderBookEntity.toData() = OrderBookData(
    asks = asks.map(AskEntity::toData),
    bids = bids.map(BidEntity::toData),
    sequence = sequence,
    updated_at = updatedAt
)

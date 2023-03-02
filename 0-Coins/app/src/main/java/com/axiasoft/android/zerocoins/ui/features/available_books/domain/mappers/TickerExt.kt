package com.axiasoft.android.zerocoins.ui.features.available_books.domain.mappers

import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.ticker.Ticker
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.ticker.entity.TickerEntity
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.ticker.response.TickerResponse

fun TickerResponse.toDomain(): Ticker {
    return Ticker(
        book = this.book ?: "",
        high = this.high,
        last = this.last,
        createdAt = this.createdAt,
        volume = this.volume,
        vwap = this.vwap,
        low = this.low,
        ask = this.ask,
        bid = this.bid,
        change24 = this.change24,
    )
}

fun Ticker.toEntity(): TickerEntity {
    return TickerEntity(
        book = this.book ?: "",
        high = this.high,
        last = this.last,
        createdAt = this.createdAt,
        volume = this.volume,
        vwap = this.vwap,
        low = this.low,
        ask = this.ask,
        bid = this.bid,
        change24 = this.change24,
    )
}

fun TickerEntity.toDomain(): Ticker {
    return Ticker(
        book = this.book,
        high = this.high,
        last = this.last,
        createdAt = this.createdAt,
        volume = this.volume,
        vwap = this.vwap,
        low = this.low,
        ask = this.ask,
        bid = this.bid,
        change24 = this.change24,
    )
}

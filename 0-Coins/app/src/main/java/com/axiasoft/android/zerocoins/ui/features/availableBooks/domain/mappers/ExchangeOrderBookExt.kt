package com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.mappers

import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.exchange_order_book.ExchangeOrderBook
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.exchange_order_book.entity.ExchangeOrderBookEntity
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.exchange_order_book.response.ExchangeOrderBookResponse

fun ExchangeOrderBookEntity.toDomain(): ExchangeOrderBook {
    return ExchangeOrderBook(
        book = book,
        minimumAmount = minimumAmount,
        maximumAmount = maximumAmount,
        minimumPrice = minimumPrice,
        maximumPrice = maximumPrice,
        minimumValue = minimumValue,
        maximumValue = maximumValue,
    )
}

fun ExchangeOrderBookResponse.toDomain(): ExchangeOrderBook {
    return ExchangeOrderBook(
        book = book,
        minimumAmount = minimumAmount,
        maximumAmount = maximumAmount,
        minimumPrice = minimumPrice,
        maximumPrice = maximumPrice,
        minimumValue = minimumValue,
        maximumValue = maximumValue,
    )
}

fun ExchangeOrderBook.toEntity(): ExchangeOrderBookEntity {
    return ExchangeOrderBookEntity(
        book = book ?: "",
        minimumAmount = minimumAmount,
        maximumAmount = maximumAmount,
        minimumPrice = minimumPrice,
        maximumPrice = maximumPrice,
        minimumValue = minimumValue,
        maximumValue = maximumValue,
    )
}

package com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.mappers

import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.exchangeOrderBook.ExchangeOrderBook
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.exchangeOrderBook.entity.ExchangeOrderBookEntity
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.exchangeOrderBook.response.ExchangeOrderBookResponse

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

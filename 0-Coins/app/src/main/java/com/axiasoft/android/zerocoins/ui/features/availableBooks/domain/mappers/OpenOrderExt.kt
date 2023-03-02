package com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.mappers

import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.openOrdersBook.Ask
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.openOrdersBook.Bids
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.openOrdersBook.entity.AskEntity
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.openOrdersBook.entity.BidsEntity

fun Ask.toEntity(): AskEntity {
    return AskEntity(
        book = this.book ?: "",
        price = this.price ?: "",
        amount = this.amount ?: "",
    )
}

fun AskEntity.toDomain(): Ask {
    return Ask(
        book = this.book,
        price = this.price,
        amount = this.amount,
    )
}

fun Bids.toEntity(): BidsEntity {
    return BidsEntity(
        book = this.book ?: "",
        price = this.price ?: "",
        amount = this.amount ?: "",
    )
}

fun BidsEntity.toDomain(): Bids {
    return Bids(
        book = this.book,
        price = this.price,
        amount = this.amount,
    )
}

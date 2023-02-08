package com.javg.cryptocurrencies.data.mapper

import com.javg.cryptocurrencies.data.db.entity.CRYBookEntity
import com.javg.cryptocurrencies.data.model.CRYBook
import com.javg.cryptocurrencies.data.model.CRYBookResponse

fun CRYBookResponse.toEntity(): CRYBookEntity{
    return CRYBookEntity(
        book = this.book,
        minimumPrice = this.minimumPrice,
        maximumPrice = this.maximumPrice)
}

fun CRYBookEntity.toDomain(): CRYBook{
    return CRYBook(
        book = this.book,
        imageUrl = "")
}
package com.javg.cryptocurrencies.data.mapper

import com.javg.cryptocurrencies.data.db.entity.CRYBookEntity
import com.javg.cryptocurrencies.data.model.CRYBook
import com.javg.cryptocurrencies.data.model.CRYBookResponse

/**
 * A function is extended to be able to treat the model
 * of the response to an entity model
 */
fun CRYBookResponse.toEntity(): CRYBookEntity{
    return CRYBookEntity(
        book = this.book,
        minimumPrice = this.minimumPrice,
        maximumPrice = this.maximumPrice)
}

/**
 * A function is extended from the entity model to
 * handle it and pass it to a general model
 */
fun CRYBookEntity.toDomain(): CRYBook{
    return CRYBook(
        book = this.book,
        imageUrl = "")
}
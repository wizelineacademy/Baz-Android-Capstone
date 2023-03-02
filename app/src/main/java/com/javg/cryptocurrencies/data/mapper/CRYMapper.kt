package com.javg.cryptocurrencies.data.mapper

import com.javg.cryptocurrencies.data.db.entity.CRYBookEntity
import com.javg.cryptocurrencies.data.db.entity.CRYDetailBookEntity
import com.javg.cryptocurrencies.data.model.CRYBook
import com.javg.cryptocurrencies.data.model.CRYBookResponse
import com.javg.cryptocurrencies.data.model.CRYDetailBook
import com.javg.cryptocurrencies.data.model.CRYTicker
import com.javg.cryptocurrencies.utils.CRYUtils

/**
 * A function is extended to be able to treat the model
 * of the response to an entity model
 */
fun CRYBookResponse.toEntity(): CRYBookEntity {
    return CRYBookEntity(
        book = this.book,
        minimumPrice = this.minimumPrice,
        maximumPrice = this.maximumPrice,
    )
}

/**
 * A function is extended from the entity model to
 * handle it and pass it to a general model
 */
fun CRYBookEntity.toDomain(): CRYBook {
    return CRYBook(
        book = this.book,
        imageUrl = "",
        bookDestination = "",
    )
}

/**
 * A function is extended to be able to treat the model
 * of the response to an entity model
 */
fun CRYTicker.toEntity(): CRYDetailBookEntity {
    return CRYDetailBookEntity(
        book = this.book,
        high = this.high,
        last = this.last,
        low = this.low,
        askList = "",
        bidsList = "",
    )
}

/**
 * A function is extended from the entity model to
 * handle it and pass it to a general model
 */
fun CRYDetailBookEntity.toDomain(): CRYDetailBook {
    return CRYDetailBook(
        high = this.high,
        last = this.last,
        low = this.low,
    )
}

/**
 * An entity model function is extended to handle it and pass
 * it to a general model by updating missing data
 */
fun CRYDetailBookEntity.toDomainAll(): CRYDetailBook {
    return CRYDetailBook(
        high = this.high,
        last = this.last,
        low = this.low,
        askList = CRYUtils.convertersJsonToList(this.askList),
        bidsList = CRYUtils.convertersJsonToList(this.bidsList),
    )
}

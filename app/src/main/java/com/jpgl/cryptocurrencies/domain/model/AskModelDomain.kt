package com.jpgl.cryptocurrencies.domain.model

import com.jpgl.cryptocurrencies.data.database.entities.AsksEntity
import com.jpgl.cryptocurrencies.data.database.entities.BidsEntity
import com.jpgl.cryptocurrencies.data.model.AsksModel
import com.jpgl.cryptocurrencies.data.model.BidsModel

data class AsksModelDomain(
    var bookAsks: String,
    val priceAsks: String,
    val amountAsks: String
)

fun AsksModel.toDomain() = AsksModelDomain(bookAsks, priceAsks, amountAsks)
fun AsksEntity.toDomain() = AsksModelDomain(bookAsks, priceAsks, amountAsks)
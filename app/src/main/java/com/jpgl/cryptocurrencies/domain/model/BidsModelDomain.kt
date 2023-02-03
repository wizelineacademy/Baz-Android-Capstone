package com.jpgl.cryptocurrencies.domain.model

import com.jpgl.cryptocurrencies.data.database.entities.BidsEntity
import com.jpgl.cryptocurrencies.data.model.BidsModel

data class BidsModelDomain(
    var bookBids: String,
    val priceBids: String,
    val amountBids: String
)

fun BidsModel.toDomain() = BidsModelDomain(bookBids, priceBids, amountBids)
fun BidsEntity.toDomain() = BidsModelDomain(bookBids, priceBids, amountBids)
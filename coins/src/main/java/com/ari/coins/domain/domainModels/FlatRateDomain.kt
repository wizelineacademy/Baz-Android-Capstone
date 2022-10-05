package com.ari.coins.domain.domainModels

import com.ari.coins.data.models.FlatRateData

data class FlatRateDomain(
    val maker: String,
    val taker: String
)

fun FlatRateData.toDomain() = FlatRateDomain(maker, taker)

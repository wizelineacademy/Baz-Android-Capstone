package com.ari.coins.framework.data.local.entities

import com.ari.coins.data.models.FlatRateData

data class FlatRateEntity(
    val maker: String,
    val taker: String
)

fun FlatRateData.toEntity() = FlatRateEntity(maker, taker)

fun FlatRateEntity.toData() = FlatRateData(maker, taker)

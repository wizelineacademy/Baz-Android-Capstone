package com.ari.coins.domain.domainModels

import com.ari.coins.data.models.StructureData

data class StructureDomain(
    val maker: String,
    val taker: String,
    val volume: String
)

fun StructureData.toDomain() = StructureDomain(maker, taker, volume)

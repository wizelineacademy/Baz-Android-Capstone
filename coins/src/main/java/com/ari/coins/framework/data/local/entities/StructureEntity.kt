package com.ari.coins.framework.data.local.entities

import com.ari.coins.data.models.StructureData

data class StructureEntity(
    val maker: String,
    val taker: String,
    val volume: String
)

fun StructureData.toEntity() = StructureEntity(maker, taker, volume)

fun StructureEntity.toData() = StructureData(maker, taker, volume)

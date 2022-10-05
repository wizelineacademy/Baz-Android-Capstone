package com.ari.coins.framework.data.local.entities

import com.ari.coins.data.models.FeesData
import com.ari.coins.data.models.StructureData

data class FeesEntity(
    val flatRate: FlatRateEntity,
    val structure: List<StructureEntity>
)

fun FeesData.toEntity() = FeesEntity(
    flatRate = flat_rate.toEntity(),
    structure = structure.map(StructureData::toEntity)
)

fun FeesEntity.toData() = FeesData(
    flat_rate = flatRate.toData(),
    structure = structure.map(StructureEntity::toData)
)

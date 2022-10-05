package com.ari.coins.domain.domainModels

import com.ari.coins.data.models.FeesData

data class FeesDomain(
    val flatRate: FlatRateDomain,
    val structure: List<StructureDomain>
)

fun FeesData.toDomain() = FeesDomain(
    flatRate = flat_rate.toDomain(),
    structure = structure.map { it.toDomain() }
)

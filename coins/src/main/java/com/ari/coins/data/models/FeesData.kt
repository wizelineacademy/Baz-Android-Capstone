package com.ari.coins.data.models

data class FeesData(
    val flat_rate: FlatRateData,
    val structure: List<StructureData>
)

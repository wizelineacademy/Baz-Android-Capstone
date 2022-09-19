package com.ari.coins.data.models

data class Fees(
    val flat_rate: FlatRate,
    val structure: List<Structure>
)
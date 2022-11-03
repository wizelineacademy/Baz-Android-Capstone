package com.course.criptomonedas.data.models

data class Fees(
    val flat_rate: FlatRate,
    val structure: List<Structure>
)
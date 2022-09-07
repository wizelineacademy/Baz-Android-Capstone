package com.example.capproject.models.book

data class Fees(
    val flat_rate: FlatRate,
    val structure: List<Structure>
)
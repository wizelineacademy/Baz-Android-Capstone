package com.example.capproject.models.Book

data class Fees(
    val flat_rate: FlatRate,
    val structure: List<Structure>
)
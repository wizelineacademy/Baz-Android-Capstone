package com.example.capproject.models.Book

data class Books(
    val payload: List<Payload>,
    val success: Boolean,
    val error: ErrorX
)
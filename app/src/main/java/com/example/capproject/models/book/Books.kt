package com.example.capproject.models.book

import com.example.capproject.models.book.Payload

data class Books(
    val payload: List<Payload>,
    val success: Boolean
)
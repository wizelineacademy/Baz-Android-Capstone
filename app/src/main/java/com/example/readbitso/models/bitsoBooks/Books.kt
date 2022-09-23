package com.example.readbitso.models.bitsoBooks
import com.example.readbitso.models.ErrorX

data class Books(
    val payload: List<BooksPayload>,
    val success: Boolean,
    val error: ErrorX
)
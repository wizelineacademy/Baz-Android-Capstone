package com.example.readbitso.models.bitsoModels.bitsoBooks

data class Books(
    val payload: List<BooksPayload>,
    val success: Boolean,
    val error: ErrorX
)

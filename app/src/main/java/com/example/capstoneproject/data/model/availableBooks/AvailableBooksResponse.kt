package com.example.capstoneproject.data.model.availableBooks

data class AvailableBooksResponse(
    val payload: List<AvailableBookModel>,
    val success: Boolean
)
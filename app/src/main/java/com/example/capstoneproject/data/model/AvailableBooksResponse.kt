package com.example.capstoneproject.data.model

data class AvailableBooksResponse(
    val payload: List<AvailableBookModel>,
    val success: Boolean
)
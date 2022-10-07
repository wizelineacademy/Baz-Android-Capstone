package com.example.cryptocurrency_challenge.repository

import com.example.cryptocurrency_challenge.data.model.Available_books_response

interface AvailableBooksRepository {
    suspend fun getAvailable_books() : Available_books_response
}

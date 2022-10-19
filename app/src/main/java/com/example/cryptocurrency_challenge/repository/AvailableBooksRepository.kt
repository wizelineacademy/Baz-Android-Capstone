package com.example.cryptocurrency_challenge.repository

import com.example.cryptocurrency_challenge.data.model.Payload

interface AvailableBooksRepository {
    suspend fun getAvailableBooks() : List<Payload>
}

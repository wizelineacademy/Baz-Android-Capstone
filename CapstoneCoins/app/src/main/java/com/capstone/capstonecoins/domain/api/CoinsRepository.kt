package com.capstone.capstonecoins.domain.api

import com.capstone.capstonecoins.data.models.availablebooks.BooksDto

interface CoinsRepository {
    suspend fun getAvailableBooks(): BooksDto
}
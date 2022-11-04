package com.capstone.capstonecoins.domain.api

import com.capstone.capstonecoins.data.repository.models.Book

interface CoinsRepository {
    suspend fun getAvailableBooks(): List<Book>
}
package com.capstone.capstonecoins.domain.api

import com.capstone.capstonecoins.data.repository.models.Book

interface CoinsRepository {
    suspend fun getAvailableBooks(): List<Book>
    suspend fun insertLocalBooks(book: List<Book>)
    suspend fun getLocalBooks(): List<Book>
}
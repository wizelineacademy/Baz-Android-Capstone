package com.capstone.capstonecoins.data.repository

import com.capstone.capstonecoins.data.models.availablebooks.BooksDto
import com.capstone.capstonecoins.domain.api.ApiService
import com.capstone.capstonecoins.domain.api.CoinsRepository

class CoinsRepositoryImpl(private val api: ApiService) : CoinsRepository {

    override suspend fun getAvailableBooks(): BooksDto =
        api.getAvailableBooks()


}
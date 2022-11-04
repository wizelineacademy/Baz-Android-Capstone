package com.capstone.capstonecoins.data.repository

import com.capstone.capstonecoins.data.repository.models.Book
import com.capstone.capstonecoins.data.utils.toBooks
import com.capstone.capstonecoins.domain.api.ApiService
import com.capstone.capstonecoins.domain.api.CoinsRepository

class CoinsRepositoryImpl(private val api: ApiService) : CoinsRepository {

    //Todo Agregar dataSource
    override suspend fun getAvailableBooks(): List<Book> =
        api.getAvailableBooks().toBooks()

}
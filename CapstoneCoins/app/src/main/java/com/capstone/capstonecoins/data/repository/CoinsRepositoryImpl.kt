package com.capstone.capstonecoins.data.repository

import com.capstone.capstonecoins.data.repository.models.Book
import com.capstone.capstonecoins.data.utils.toBooks
import com.capstone.capstonecoins.domain.api.ApiService
import com.capstone.capstonecoins.domain.api.BooksDao
import com.capstone.capstonecoins.domain.api.CoinsRepository
import javax.inject.Inject

class CoinsRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dao: BooksDao
) : CoinsRepository {
    override suspend fun getAvailableBooks(): List<Book> =
        api.getAvailableBooks().toBooks()

    override suspend fun insertLocalBooks(book: List<Book>) =
        dao.insertAllBooks(book)

    override suspend fun getLocalBooks(): List<Book> =
        dao.getAvailableLocalBooks()


}
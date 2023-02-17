package com.example.baz_android_capstone.data.repository.local

import com.example.baz_android_capstone.data.models.roomModel.BookDetails
import kotlinx.coroutines.flow.Flow

interface BookDatabaseRepositoryInterface {
    suspend fun getBooksFromDB(): Flow<List<BookDetails>>
    suspend fun saveBooksToDB(books: List<BookDetails>)
    suspend fun deleteBooksInDB()
}
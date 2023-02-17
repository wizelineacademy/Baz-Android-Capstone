package com.example.baz_android_capstone.data.repository.local

import com.example.baz_android_capstone.data.db.BookDao
import com.example.baz_android_capstone.data.models.roomModel.BookDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookDatabaseRepository @Inject constructor(private val dao: BookDao) : BookDatabaseRepositoryInterface {
    override suspend fun getBooksFromDB(): Flow<List<BookDetails>> = dao.getBooks()

    override suspend fun saveBooksToDB(books: List<BookDetails>) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.saveBooks(books)
        }
    }

    override suspend fun deleteBooksInDB() {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteBooks()
        }
    }
}
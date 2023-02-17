package com.example.baz_android_capstone.data.repository.cache

import com.example.baz_android_capstone.data.models.roomModel.BookDetails
import javax.inject.Inject

class CacheRepository @Inject constructor() : CacheRepositoryInterface {
    private var bookList = ArrayList<BookDetails>()

    override suspend fun getBooksFromCache(): List<BookDetails> = bookList

    override suspend fun saveBooksToCache(books: List<BookDetails>) {
        bookList.clear()
        bookList = ArrayList(books)
    }
}

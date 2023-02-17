package com.example.baz_android_capstone.data.repository.cache

import com.example.baz_android_capstone.data.models.roomModel.BookDetails

interface CacheRepositoryInterface {
    suspend fun getBooksFromCache(): List<BookDetails>
    suspend fun saveBooksToCache(books: List<BookDetails>)
}
package com.example.baz_android_capstone.data.repository

import com.example.baz_android_capstone.data.models.roomModel.BookDetails

interface RepositoryInterface {
    suspend fun getBooks(): List<BookDetails>?
    suspend fun updateBooks(): List<BookDetails>?
}
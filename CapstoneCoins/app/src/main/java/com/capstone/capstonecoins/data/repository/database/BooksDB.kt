package com.capstone.capstonecoins.data.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.capstone.capstonecoins.data.models.availablebooks.Payload
import com.capstone.capstonecoins.data.repository.models.Book
import com.capstone.capstonecoins.data.repository.models.BookDetail
import com.capstone.capstonecoins.domain.api.BooksDao

@Database(entities = [Payload::class, Book::class, BookDetail::class], version = 1)
abstract class BooksDB : RoomDatabase() {
    abstract fun booksDao(): BooksDao
}
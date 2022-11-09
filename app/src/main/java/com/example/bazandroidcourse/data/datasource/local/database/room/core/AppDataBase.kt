package com.example.bazandroidcourse.data.datasource.local.database.room.core


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bazandroidcourse.data.datasource.local.database.room.dao.BookDetailsDao
import com.example.bazandroidcourse.data.datasource.local.database.room.dao.BookOrdersDao
import com.example.bazandroidcourse.data.datasource.local.database.room.dao.BooksDao
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookDetailEntity
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookEntity
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookOrderEntity


@Database(
    entities = [
        BookEntity::class,
        BookDetailEntity::class,
        BookOrderEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase: RoomDatabase(){
    abstract fun booksDao()         : BooksDao
    abstract fun bookDetailsDao()   : BookDetailsDao
    abstract fun BookOrdersDao()    : BookOrdersDao

}





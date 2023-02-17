package com.example.baz_android_capstone.data.db // ktlint-disable package-name

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.baz_android_capstone.data.models.roomModel.AskConverters
import com.example.baz_android_capstone.data.models.roomModel.BidConverters
import com.example.baz_android_capstone.data.models.roomModel.BookDetails

@Database(entities = [BookDetails::class], version = 1, exportSchema = false)
@TypeConverters(AskConverters::class, BidConverters::class)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}

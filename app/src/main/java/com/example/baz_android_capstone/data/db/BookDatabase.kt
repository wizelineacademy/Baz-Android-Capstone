package com.example.baz_android_capstone.data.db // ktlint-disable package-name

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.baz_android_capstone.data.models.availableBook.Book
import com.example.baz_android_capstone.data.models.availableBook.BookConverter
import com.example.baz_android_capstone.data.models.orderBook.AskConverters
import com.example.baz_android_capstone.data.models.orderBook.BidConverters
import com.example.baz_android_capstone.data.models.orderBook.OrderBook
import com.example.baz_android_capstone.data.models.orderBook.OrderConverter
import com.example.baz_android_capstone.data.models.ticker.Ticker
import com.example.baz_android_capstone.data.models.ticker.TickerConverter

@Database(
    entities = [Book::class, OrderBook::class, Ticker::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(
    BookConverter::class,
    AskConverters::class,
    BidConverters::class,
    OrderConverter::class,
    TickerConverter::class
)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun orderDao(): OrderDao
    abstract fun tickerDao(): TickerDao
}

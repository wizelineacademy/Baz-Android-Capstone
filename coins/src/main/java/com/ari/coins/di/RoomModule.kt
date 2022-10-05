package com.ari.coins.di

import android.content.Context
import androidx.room.Room
import com.ari.coins.framework.data.local.dao.AvailableBookDao
import com.ari.coins.framework.data.local.dao.OrderBookDao
import com.ari.coins.framework.data.local.dao.TickerDao
import com.ari.coins.framework.data.local.db.AvailableBookDB
import com.ari.coins.framework.data.local.db.OrderBookDB
import com.ari.coins.framework.data.local.db.TickerDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Ari Valencia
 * @file RoomModule
 * @description Provide DAO and DB with Room
 */

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val AVAILABLE_BOOK_DB = "AVAILABLE_BOOK_DB"
    private const val ORDER_BOOK_DB = "ORDER_BOOK_DB"
    private const val TICKER_DB = "TICKER_DB"

    @Singleton
    @Provides
    fun provideAvailableBookDB(@ApplicationContext context: Context): AvailableBookDB = Room
        .databaseBuilder(context, AvailableBookDB::class.java, AVAILABLE_BOOK_DB)
        .build()

    @Singleton
    @Provides
    fun provideOrderBookDB(@ApplicationContext context: Context): OrderBookDB = Room
        .databaseBuilder(context, OrderBookDB::class.java, ORDER_BOOK_DB)
        .build()

    @Singleton
    @Provides
    fun provideTickerDB(@ApplicationContext context: Context): TickerDB = Room
        .databaseBuilder(context, TickerDB::class.java, TICKER_DB)
        .build()

    @Singleton
    @Provides
    fun provideAvailableBookDao(availableBookDB: AvailableBookDB): AvailableBookDao =
        availableBookDB.getAvailableBookDao()

    @Singleton
    @Provides
    fun provideOrderBookDao(orderBookDB: OrderBookDB): OrderBookDao = orderBookDB.getOrderBookDao()

    @Singleton
    @Provides
    fun provideTickerDao(tickerDB: TickerDB): TickerDao = tickerDB.getTickerDao()
}

package com.axiasoft.android.zerocoins.ui.features.availableBooks.di

import com.axiasoft.android.zerocoins.db.ZeroCoinAppDatabase
import com.axiasoft.android.zerocoins.db.getDatabase
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.apis.BitsoOrderBooksApi
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.repositories.order_book.LocalOrderBookRepository
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.repositories.order_book.LocalOrderBookRepositoryImpl
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.repositories.order_book.RemoteOrderBooksRepository
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.repositories.order_book.RemoteOrderBooksRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BitsoBooksModule {

    @Provides
    @Singleton
    fun provideRemoteOrderBookRepository(bitsoOrderBooksApi: BitsoOrderBooksApi): RemoteOrderBooksRepository {
        return RemoteOrderBooksRepositoryImpl(bitsoOrderBooksApi)
    }

    @Provides
    @Singleton
    fun provideBitsoOrderBooksApi(): BitsoOrderBooksApi {
        return BitsoOrderBooksApi.Builder().build()
    }

    @Provides
    @Singleton
    fun provideLocalOrderBookRepository(db: ZeroCoinAppDatabase): LocalOrderBookRepository {
        return LocalOrderBookRepositoryImpl(db)
    }

    @Provides
    @Singleton
    fun providesBitsoBooksDB(): ZeroCoinAppDatabase {
        return getDatabase()
    }
}

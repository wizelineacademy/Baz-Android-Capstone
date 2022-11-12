package com.example.bazandroidcourse.data.di

import com.example.bazandroidcourse.data.datasource.local.CollectionLocaleDataSourceInterface
import com.example.bazandroidcourse.data.datasource.local.GeneralLocalDataSourceInterface
import com.example.bazandroidcourse.data.datasource.local.RowByIdLocaleDataSourceInterface
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookDetailEntity
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookEntity
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookOrderEntity
import com.example.bazandroidcourse.data.datasource.remote.CryptoRemoteDataSourceInterface
import com.example.bazandroidcourse.data.repository.BooksRepositoryImpl
import com.example.bazandroidcourse.data.repository.BooksRepositoryInterface
import com.example.bazandroidcourse.data.utils.network.NetworkManagerInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        localeBooksDataSource: GeneralLocalDataSourceInterface<BookEntity>,
        localeDetailDataSource: RowByIdLocaleDataSourceInterface<BookDetailEntity, String>,
        localeOrdersDataSource: CollectionLocaleDataSourceInterface<BookOrderEntity, String>,
        remoteDataSource: CryptoRemoteDataSourceInterface,
        networkManager:NetworkManagerInterface
    ): BooksRepositoryInterface {
        return BooksRepositoryImpl(
            localeBooksDataSource,
            localeDetailDataSource,
            localeOrdersDataSource,
            remoteDataSource,
            networkManager
        )
    }

}



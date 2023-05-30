package com.androidcourse.data.di


import com.andcourse.domain.repository.BooksRepositoryInterface
import com.androidcourse.data.datasource.local.CollectionLocaleDataSourceInterface
import com.androidcourse.data.datasource.local.RXInterface
import com.androidcourse.data.datasource.local.RowByIdLocaleDataSourceInterface
import com.androidcourse.data.datasource.local.database.room.entities.BookDetailEntity
import com.androidcourse.data.datasource.local.database.room.entities.BookEntity
import com.androidcourse.data.datasource.local.database.room.entities.BookOrderEntity
import com.androidcourse.data.datasource.remote.CryptoRemoteDataSourceInterface
import com.androidcourse.data.repository.BooksRepositoryImpl
import com.androidcourse.data.utils.network.NetworkManagerInterface
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
        localeBooksDataSource: RXInterface<BookEntity>,
        localeDetailDataSource: RowByIdLocaleDataSourceInterface<BookDetailEntity, String>,
        localeOrdersDataSource: CollectionLocaleDataSourceInterface<BookOrderEntity, String>,
        remoteDataSource: CryptoRemoteDataSourceInterface,
        networkManager: NetworkManagerInterface
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

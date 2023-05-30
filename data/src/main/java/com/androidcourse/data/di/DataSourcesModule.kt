package com.androidcourse.data.di

import com.androidcourse.data.datasource.local.CollectionLocaleDataSourceInterface
import com.androidcourse.data.datasource.local.GeneralLocalDataSourceInterface
import com.androidcourse.data.datasource.local.RXInterface
import com.androidcourse.data.datasource.local.RowByIdLocaleDataSourceInterface
import com.androidcourse.data.datasource.local.database.room.BookDetailsLocalDatasourceImpl
import com.androidcourse.data.datasource.local.database.room.BookOrdersLocalDataSourceImpl
import com.androidcourse.data.datasource.local.database.room.BooksLocalDataSourceImpl
import com.androidcourse.data.datasource.local.database.room.dao.BookDetailsDao
import com.androidcourse.data.datasource.local.database.room.dao.BookOrdersDao
import com.androidcourse.data.datasource.local.database.room.dao.BooksDao
import com.androidcourse.data.datasource.local.database.room.entities.BookDetailEntity
import com.androidcourse.data.datasource.local.database.room.entities.BookEntity
import com.androidcourse.data.datasource.local.database.room.entities.BookOrderEntity
import com.androidcourse.data.datasource.remote.CryptoRemoteDataSourceImpl
import com.androidcourse.data.datasource.remote.CryptoRemoteDataSourceInterface
import com.androidcourse.data.datasource.remote.api.retrofit.ApplicationAPIInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object DataSourcesModule {

    @Singleton
    @Provides
    fun provideBooksLocalDataSource(
        dao: BooksDao
    ): GeneralLocalDataSourceInterface<BookEntity> {
        return BooksLocalDataSourceImpl(
            dao
        )
    }

    @Singleton
    @Provides
    fun provideBooksLocalDataSourceRx(
        dao: BooksDao
    ): RXInterface<BookEntity> {
        return BooksLocalDataSourceImpl(
            dao
        )
    }

    @Singleton
    @Provides
    fun provideBookDetailsLocalDataSource(
        dao: BookDetailsDao
    ): RowByIdLocaleDataSourceInterface<BookDetailEntity, String> {
        return BookDetailsLocalDatasourceImpl(
            dao
        )
    }

    @Singleton
    @Provides
    fun provideBookOrdersDataSource(
        dao: BookOrdersDao
    ): CollectionLocaleDataSourceInterface<BookOrderEntity, String> {
        return BookOrdersLocalDataSourceImpl(
            dao
        )
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        apiInterface: ApplicationAPIInterface,
        @ApplicationScope externalScope: CoroutineScope
    ): CryptoRemoteDataSourceInterface {
        return CryptoRemoteDataSourceImpl(
            apiInterface,
            externalScope
        )
    }
}

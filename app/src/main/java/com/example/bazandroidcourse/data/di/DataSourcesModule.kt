package com.example.bazandroidcourse.data.di

import com.example.bazandroidcourse.data.datasource.local.CollectionLocaleDataSourceInterface
import com.example.bazandroidcourse.data.datasource.local.GeneralLocalDataSourceInterface
import com.example.bazandroidcourse.data.datasource.local.RowByIdLocaleDataSourceInterface
import com.example.bazandroidcourse.data.datasource.local.database.room.BookDetailsLocalDatasourceImpl
import com.example.bazandroidcourse.data.datasource.local.database.room.BookOrdersLocalDataSourceImpl
import com.example.bazandroidcourse.data.datasource.local.database.room.BooksLocalDataSourceImpl
import com.example.bazandroidcourse.data.datasource.local.database.room.dao.BookDetailsDao
import com.example.bazandroidcourse.data.datasource.local.database.room.dao.BookOrdersDao
import com.example.bazandroidcourse.data.datasource.local.database.room.dao.BooksDao
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookDetailEntity
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookEntity
import com.example.bazandroidcourse.data.datasource.local.database.room.entities.BookOrderEntity
import com.example.bazandroidcourse.data.datasource.remote.CryptoRemoteDataSourceImpl
import com.example.bazandroidcourse.data.datasource.remote.CryptoRemoteDataSourceInterface
import com.example.bazandroidcourse.data.datasource.remote.api.retrofit.ApplicationAPIInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
@Module
@InstallIn(ActivityComponent::class)
abstract class DataSourcesModule {

    @Binds
    abstract fun bindBooksLocalDataSource(
        implementation: BooksLocalDataSourceImpl
    ): BooksInterface // GeneralLocalDataSourceInterface<BookEntity>

    @Binds
    abstract  fun binLocaleDetailDataSource(
        implementation:  BookDetailsLocalDatasourceImpl
    ): BookDetailsInterface//RowByIdLocaleDataSourceInterface<BookDetailEntity, String>

    @Binds
    abstract fun bindLocaleOrdersDataSource(
        implementation: BookOrdersLocalDataSourceImpl//CollectionLocaleDataSourceInterface<BookOrderEntity, String>
    ):BookOrdersInterface//CollectionLocaleDataSourceInterface<BookOrderEntity, String>

    @Binds
    abstract fun bindRemoteDataSource(
        implementation: CryptoRemoteDataSourceImpl
    ): CryptoRemoteDataSourceInterface

}*/

@Module
@InstallIn(SingletonComponent::class)
object DataSourcesModule {

    @Singleton
    @Provides
    fun provideBooksLocalDataSource(
        dao: BooksDao
    ):      GeneralLocalDataSourceInterface<BookEntity>
            //BooksInterface
    {
        return BooksLocalDataSourceImpl(
                dao
        )
    }

    @Singleton
    @Provides
    fun provideBookDetailsLocalDataSource(
        dao: BookDetailsDao
    ):
     //BookDetailsInterface
      RowByIdLocaleDataSourceInterface<BookDetailEntity,String>
    {
        return BookDetailsLocalDatasourceImpl(
            dao
        )
    }

    @Singleton
    @Provides
    fun provideBookOrdersDataSource(
        dao: BookOrdersDao
    ):
    CollectionLocaleDataSourceInterface<BookOrderEntity,String>
    //        BookOrdersInterface
    {
        return BookOrdersLocalDataSourceImpl(
            dao
        )
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        apiInterface: ApplicationAPIInterface
    ): CryptoRemoteDataSourceInterface {
        return CryptoRemoteDataSourceImpl(
            apiInterface
        )
    }

}

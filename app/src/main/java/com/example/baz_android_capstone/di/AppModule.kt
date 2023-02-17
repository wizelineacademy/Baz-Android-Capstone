package com.example.baz_android_capstone.di // ktlint-disable package-name

import android.content.Context
import androidx.room.Room
import com.example.baz_android_capstone.data.db.BookDao
import com.example.baz_android_capstone.data.db.BookDatabase
import com.example.baz_android_capstone.data.network.BookAPI
import com.example.baz_android_capstone.data.repository.Repository
import com.example.baz_android_capstone.data.repository.cache.CacheRepository
import com.example.baz_android_capstone.data.repository.cache.CacheRepositoryInterface
import com.example.baz_android_capstone.data.repository.local.BookDatabaseRepository
import com.example.baz_android_capstone.data.repository.local.BookDatabaseRepositoryInterface
import com.example.baz_android_capstone.data.repository.remote.BookRepository
import com.example.baz_android_capstone.data.repository.remote.BookRepositoryInterface
import com.example.baz_android_capstone.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRepository(
        bookRepository: BookRepository,
        bookDatabaseRepository: BookDatabaseRepository,
        cacheRepository: CacheRepository
    ): Repository =
        Repository(bookRepository, bookDatabaseRepository, cacheRepository)

    @Singleton
    @Provides
    fun provideBookRepository(api: BookAPI): BookRepositoryInterface = BookRepository(api)

    @Singleton
    @Provides
    fun provideBookDatabaseRepository(dao: BookDao): BookDatabaseRepositoryInterface = BookDatabaseRepository(dao)

    @Singleton
    @Provides
    fun provideCacheRepository(): CacheRepositoryInterface = CacheRepository()

    @Singleton
    @Provides
    fun provideBookApi(okHttpClient: OkHttpClient): BookAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.AVAILABLE_BOOK_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesBookDao(bookDatabase: BookDatabase): BookDao = bookDatabase.bookDao()

    @Singleton
    @Provides
    fun provideBookDatabase(@ApplicationContext context: Context): BookDatabase =
        Room.databaseBuilder(
            context = context,
            klass = BookDatabase::class.java,
            name = "book_db"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
}

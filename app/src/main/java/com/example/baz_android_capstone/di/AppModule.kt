package com.example.baz_android_capstone.di // ktlint-disable package-name

import android.content.Context
import androidx.room.Room
import com.example.baz_android_capstone.data.db.BookDatabase
import com.example.baz_android_capstone.data.network.BookAPI
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
    fun provideBookApi(okHttpClient: OkHttpClient): BookAPI {
        return Retrofit.Builder()
            .baseUrl(BookAPI.AVAILABLE_BOOK_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookAPI::class.java)
    }

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

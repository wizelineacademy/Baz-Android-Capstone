package com.example.baz_android_capstone.di

import com.example.baz_android_capstone.data.network.BookAPI
import com.example.baz_android_capstone.data.repository.BookRepository
import com.example.baz_android_capstone.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideBookRepository(api: BookAPI) = BookRepository(api)

    @Singleton
    @Provides
    fun provideBookApi(): BookAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.AVAILABLE_BOOK_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookAPI::class.java)
    }
}

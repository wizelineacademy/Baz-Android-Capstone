package com.example.wizelineandroid.di

import android.app.Application
import android.content.Context
import com.example.wizelineandroid.data.remote.BooksDataSource
import com.example.wizelineandroid.repository.WebService
import com.example.wizelineandroid.repository.available.BooksRepo
import com.example.wizelineandroid.repository.available.BooksRepoImpl
import com.example.wizelineandroid.repository.ticker.TickerRepo
import com.example.wizelineandroid.repository.ticker.TickerRepoImpl
import com.example.wizelineandroid.utils.AppConstants
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun providesRepository(repositoryImpl: BooksRepoImpl): BooksRepo

    companion object {

        @Provides
        fun providesOkHttpClient(@ApplicationContext context: Context): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().also { it.setLevel(HttpLoggingInterceptor.Level.HEADERS) })
                .build()

        @Provides
        fun providesRetrofitInstance(client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .client(client)
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        }

        @Provides
        fun providesCurrencyService(retrofit: Retrofit) = retrofit.create<WebService>()
    }
}
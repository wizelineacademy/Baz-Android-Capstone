package com.course.criptomonedas.di

import android.app.Application
import android.content.Context
import com.course.criptomonedas.Constants
import com.course.criptomonedas.data.datasource.availablebooks.RemoteDataSource
import com.course.criptomonedas.data.datasource.availablebooks.RemoteDataSourceImpl
import com.course.criptomonedas.data.db.DataBaseCriptos
import com.course.criptomonedas.data.db.dao.BooksDao
import com.course.criptomonedas.data.network.AvailableBooksService
import com.course.criptomonedas.data.repository.AvailableBooksRepository
import com.course.criptomonedas.data.repository.AvailableBooksRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun providesRepository(repositoryImpl: AvailableBooksRepositoryImpl): AvailableBooksRepository

    @Binds
    abstract fun providesRemoteDataSource(repositoryImpl: RemoteDataSourceImpl): RemoteDataSource

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
                .baseUrl(Constants.BASE_CRIPTOS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        fun providesCurrencyService(retrofit: Retrofit) = retrofit.create<AvailableBooksService>()

        @Singleton
        @Provides
        fun getAppDB(context: Application): DataBaseCriptos {
            return DataBaseCriptos.getDatabase(context)
        }

        @Singleton
        @Provides
        fun getDao(appDao: DataBaseCriptos): BooksDao {
            return appDao.booksDao()
        }
    }
}
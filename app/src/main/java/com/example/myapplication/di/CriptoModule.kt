package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.api.interfaces.ApiBitsoService
import com.example.myapplication.data.db.DataBase
import com.example.myapplication.data.db.dao.CriptoCurrencyDAO
import com.example.myapplication.network.NetWorkLocalData
import com.example.myapplication.network.NetWorkLocalDataImpl
import com.example.myapplication.network.NetwokDataSource
import com.example.myapplication.network.NetwokDataSourceImpl
import com.example.myapplication.repository.BitsoRepository
import com.example.myapplication.repository.BitsoRepositoryImpl
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

/**
 * Created by: Juan Antonio Amado
 * date: 24,septiembre,2022
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class CriptoModule {

    @Binds
    abstract fun providesCriptoRepository(repositoryImpl: BitsoRepositoryImpl) : BitsoRepository

    @Binds
    abstract fun provideNetworkDataSource(networkrDataSourceImpl: NetwokDataSourceImpl) :NetwokDataSource

    @Binds
    abstract fun provideNetworkLocalDataSource(networkrLocalData: NetWorkLocalDataImpl) :NetWorkLocalData

    companion object{

        @Provides
        fun providesOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().also { it.setLevel(HttpLoggingInterceptor.Level.BODY) })
                .build()

        @Provides
        fun providesRetrofitInstance(client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.bitso.com/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        fun providesRickAndMortyService(retrofit: Retrofit) = retrofit.create<ApiBitsoService>()

        @Singleton
        @Provides
        fun provideDataBase(@ApplicationContext appContext: Context): DataBase =
            DataBase.getDatabase(appContext)

        @Singleton
        @Provides
        fun provideUserDao(dataBase: DataBase): CriptoCurrencyDAO = dataBase.criptoCurrencyDAO()

    }
}
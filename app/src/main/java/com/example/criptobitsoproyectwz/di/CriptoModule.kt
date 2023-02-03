package com.example.criptobitsoproyectwz.di

import com.example.criptobitsoproyectwz.Util.Constans
import com.example.criptobitsoproyectwz.data.DataSource.criptoDataSource
import com.example.criptobitsoproyectwz.data.Repository.CriptoRepository
import com.example.criptobitsoproyectwz.data.Repository.CriptoRepositoryImpl
import com.example.criptobitsoproyectwz.data.network.BitsoService
import com.example.criptobitsoproyectwz.data.network.CriptosClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
abstract class CriptoModule {

    @Binds
    abstract fun providesCriptoRepository(criptoRepositoryImpl: CriptoRepositoryImpl): CriptoRepository

    @Binds
    abstract fun providesCriptoDataSource(criptoClient:CriptosClient): criptoDataSource

    companion object{
        @Provides
        fun provideBaseUrl(): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().also {
                    it.level = HttpLoggingInterceptor.Level.BODY
                })
                .build()

        @Provides
        fun provideRetrofitInstance(client: OkHttpClient): Retrofit{
            return Retrofit.Builder()
                .client(client)
                .baseUrl(Constans.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        fun providesCriptoService(retrofit: Retrofit) = retrofit.create<BitsoService>()
    }
}
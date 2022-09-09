package com.example.capproject.interfaces

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    @Named("Baseurl")
    fun getUri()="https://api.bitso.com/v3/"

    @Singleton
    @Provides
    fun getRetrofit(@Named("Baseurl") baseurl:String):Retrofit
    {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor).build()


        return Retrofit.Builder()
            .baseUrl(baseurl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }




    @Singleton
    @Provides
    fun restBinanceDataSource(retrofit: Retrofit):BitsoDataSource =
        retrofit.create(BitsoDataSource::class.java)
}


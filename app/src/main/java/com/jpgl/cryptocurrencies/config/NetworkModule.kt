package com.jpgl.cryptocurrencies.config

import com.jpgl.cryptocurrencies.data.webservice.CryptoApiClient
import com.jpgl.cryptocurrencies.utils.Constants.URL_BASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.logging.Level


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder().addHeader("User-Agent", "Header1").build()
                chain.proceed(newRequest)
            }
            .addNetworkInterceptor(HttpLoggingInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL_BASE)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideQuoteApiClient( retrofit: Retrofit ) : CryptoApiClient{
        return retrofit.create(CryptoApiClient::class.java)
    }

}
package com.example.cryptocurrencyapp.di

import com.example.cryptocurrencyapp.data.remote.api.CryptoApi
import com.example.cryptocurrencyapp.utils.CryptoConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent ::class)
object CryptoModule {

    @Singleton
    @Provides
    fun proverRetrofit(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(CryptoConstants.BITSO_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(oKHttpClient)
            .build()

    }

    @Singleton
    @Provides
    fun provideCryptoApi(retrofit: Retrofit): CryptoApi {
       return retrofit.create( CryptoApi::class.java)
    }

    private val oKHttpClient: OkHttpClient = OkHttpClient.Builder ().addInterceptor { chain ->
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().also {
                it.setLevel(HttpLoggingInterceptor.Level.BODY)
            }).build()
        val originalRequest = chain.request()
        val request: Request = originalRequest. newBuilder()
            .header("user_agent", originalRequest.url.host)
            .method(originalRequest.method,originalRequest.body)
            .build()
        client.newCall(request).execute()
    }.build()
}

package com.javg.cryptocurrencies.config

import android.os.Build
import com.javg.cryptocurrencies.data.network.CRYApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CRYRetrofitHelper {

    @Provides
    @Singleton
    fun providerApi(builder: Retrofit.Builder): CRYApi{
        return builder.build().create(CRYApi::class.java)
    }

    @Provides
    @Singleton
    fun providerRetrofit(): Retrofit.Builder{
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val request = chain.request()
            .newBuilder()
            .addHeader("User-Agent", "CryptocurrenciesApp")
            .build()

            chain.proceed(request)
        }

        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl(CRYApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
    }
}
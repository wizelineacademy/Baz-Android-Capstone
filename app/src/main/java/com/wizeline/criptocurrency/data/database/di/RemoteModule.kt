package com.wizeline.criptocurrency.data.database.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.wizeline.criptocurrency.data.database.CriptoCurrencyDB
import com.wizeline.criptocurrency.data.remote.dto.BitsoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.math.log

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    private const val BASE_URL = "https://api.bitso.com/v3/"

    @Singleton
    @Provides
    fun provideGson() =
        GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .setLenient()
            .create()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun providesLogginInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().also {
            it.setLevel(HttpLoggingInterceptor.Level.BODY)
        }

    @Singleton
    @Provides
    fun provideUserAgentInterceptor(): Interceptor =
        Interceptor {
            val original = it.request()
            val request: Request = original.newBuilder()
                .header("User-Agent", original.url.host)
                .method(original.method, original.body)
                .build()
            it.proceed(request)
        }

    @Singleton
    @Provides
    fun provideHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        userAgent: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(userAgent).build()


    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): BitsoApi = retrofit.create(BitsoApi::class.java)

}
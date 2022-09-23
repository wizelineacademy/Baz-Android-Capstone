package com.example.wizelineproject.modules

import android.util.Log
import com.example.wizelineproject.domain.repository.CriptosRepository
import com.example.wizelineproject.domain.repository.GenericRepository
import com.example.wizelineproject.domain.repository.HttpLogginInterceptorStock
import com.example.wizelineproject.domain.service.CriptomonedasServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesOkHttpClient():OkHttpClient{
        val interceptor = HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        val interceptorPropio = HttpLogginInterceptorStock()
        return OkHttpClient.Builder().addInterceptor(interceptorPropio).addInterceptor(interceptor).build();
    }

    @Singleton
    @Provides
    fun provideRetrofit(httpClient:OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://api.bitso.com/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provoidesCriptomonedasServices(retrofit:Retrofit):CriptomonedasServices{
        return retrofit.create(CriptomonedasServices::class.java)
    }

    @Singleton
    @Provides
    fun provideCriptosRepositoryRepository(retrofit:Retrofit, criptomonedasServices: CriptomonedasServices):CriptosRepository{
        val repo = CriptosRepository()
        repo.configRepository(retrofit, criptomonedasServices)
        return repo
    }
}
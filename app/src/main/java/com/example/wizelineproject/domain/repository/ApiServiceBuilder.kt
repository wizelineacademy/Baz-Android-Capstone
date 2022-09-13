package com.example.wizelineproject.domain.repository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


public abstract class ApiServiceBuilder<T : kotlin.Any> {

    public final var client: retrofit2.Retrofit = getRetrofit()/* compiled code */

    //private final val context: android.content.Context? /* compiled code */

    private fun getOkHttpClient():OkHttpClient{
        val interceptor = HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        val interceptorPropio = HttpLogginInterceptorStock()
        return OkHttpClient.Builder().addInterceptor(interceptorPropio).addInterceptor(interceptor).build();
    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .client(getOkHttpClient())
            .baseUrl("https://api.bitso.com/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    public abstract fun build(): T

}
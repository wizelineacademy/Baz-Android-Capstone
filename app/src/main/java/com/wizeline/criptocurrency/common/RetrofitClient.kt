package com.wizeline.criptocurrency.common.adapters

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.wizeline.criptocurrency.data.remote.dto.BitsoApi
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://api.bitso.com/v3/"

    val gson: Gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        .setLenient()
        .create()

    private val okHttpClient: OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val client: OkHttpClient = OkHttpClient.Builder()
                    .addNetworkInterceptor(
                        HttpLoggingInterceptor().also {
                            it.setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    ).build()
                val original = chain.request()
                val request: Request = original.newBuilder()
                    .header("User-Agent", original.url.host)
                    .method(original.method, original.body)
                    .build()
                client.newCall(request).execute()
            }.build()

    private fun getRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    fun repository(): BitsoApi = getRetrofit().create(BitsoApi::class.java)
}

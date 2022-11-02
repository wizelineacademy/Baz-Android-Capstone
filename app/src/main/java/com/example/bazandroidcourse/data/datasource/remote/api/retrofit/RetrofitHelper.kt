package com.example.bazandroidcourse.data.datasource.remote.api.retrofit

import android.os.Build
import com.example.bazandroidcourse.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val loggInterceptor = HttpLoggingInterceptor()
    .setLevel(HttpLoggingInterceptor.Level.BODY)

val headerInterceptor = Interceptor{ chain ->
        val request =  chain.request().newBuilder()
            .addHeader("User-Agent", System.getProperty("http.agent")?:"empty")
            .addHeader("x-device-type", Build.DEVICE)
            .build()
        chain.proceed(request)
}

val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(headerInterceptor)
    .addInterceptor(loggInterceptor)

val retrofitInstance = Retrofit.Builder()
    .baseUrl(BuildConfig.API_PRINCIPAL_PATH)
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient.build())
    .build()

val apiInstance = retrofitInstance.create(ApplicationAPIInterface::class.java)
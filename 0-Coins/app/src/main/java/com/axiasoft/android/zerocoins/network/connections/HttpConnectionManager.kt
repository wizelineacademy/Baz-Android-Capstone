package com.axiasoft.android.zerocoins.network.connections

import com.axiasoft.android.zerocoins.BuildConfig
import com.axiasoft.android.zerocoins.network.apis.CoinApis
import com.axiasoft.android.zerocoins.network.bitso.security.BitsoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

abstract class HttpConnectionManager<T : Any>(var coinApi: CoinApis) {

    abstract fun build(): T

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    val httpClient = when (coinApi) {
        CoinApis.BITSO -> {
            Retrofit.Builder()
                .baseUrl(CoinApis.BITSO.hostUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(
                    OkHttpClient().newBuilder()
                        .addInterceptor(BitsoInterceptor())
                        .addInterceptor(loggingInterceptor)
                        .build()
                ).build()
        }
        CoinApis.RESTFUL_API -> {
            Retrofit.Builder()
                .baseUrl(CoinApis.RESTFUL_API.hostUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient().newBuilder()
                        .addInterceptor(loggingInterceptor)
                        // .addInterceptor(BitsoInterceptor())
                        // .addNetworkInterceptor(GSSCHeadersInterceptorAWSS3())
                        .build()
                ).build()
        }

        else -> {
            Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient().newBuilder().build())
                .build()
        }
    }
}

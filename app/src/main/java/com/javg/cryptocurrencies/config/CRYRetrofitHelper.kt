package com.javg.cryptocurrencies.config

import com.javg.cryptocurrencies.data.network.CRYApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CRYRetrofitHelper {
    /**
     * Takes care of providing an instance of the api interface using a retrofit construct
     */
    @Provides
    @Singleton
    fun providerApi(builder: Retrofit.Builder): CRYApi {
        return builder.build().create(CRYApi::class.java)
    }

    /**
     * Provide a retrofit instance already with your build
     *
     * @param httpClient is a builder type model
     */
    @Provides
    @Singleton
    fun providerRetrofit(httpClient: OkHttpClient.Builder): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(CRYApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(httpClient.build())
    }

    /**
     * It is responsible for providing an instance of an httpClient with
     * its configuration to add a header and enable the body of the requests
     *
     * @param logging It is a model that contains the configuration to see
     * the body of the requests
     */
    @Provides
    @Singleton
    fun providerHttpClient(logging: HttpLoggingInterceptor): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("User-Agent", "CryptocurrenciesApp")
                    .build()
                chain.proceed(request)
            }.addInterceptor(logging)
    }

    /**
     * Provides the instance of an interceptor
     */
    @Provides
    @Singleton
    fun providerHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}

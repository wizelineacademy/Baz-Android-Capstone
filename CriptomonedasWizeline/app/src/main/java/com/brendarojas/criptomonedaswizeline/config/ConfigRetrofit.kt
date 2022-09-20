package com.brendarojas.criptomonedaswizeline.config

import com.brendarojas.criptomonedaswizeline.utils.Constants.URL_BASE
import com.brendarojas.criptomonedaswizeline.webservice.CryptoApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ConfigRetrofit {
    fun getConfigurationRetrofit(): CryptoApi {
        var objectRetrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var apiBooks = objectRetrofit.create(CryptoApi::class.java)

        return apiBooks
    }
}
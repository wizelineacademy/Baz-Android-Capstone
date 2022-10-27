package com.example.cryptocurrencyapp.domain.repository

import com.example.cryptocurrencyapp.data.api.WCCryptoApi
import com.example.cryptocurrencyapp.utils.WCCryptoConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
var retrofit = Retrofit.Builder()
            .baseUrl(WCCryptoConstants.BITSO_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build().create(WCCryptoApi::class.java)

//fun repository(): WCCryptoApi = getBitsoData().create(WCCryptoApi::class.java)

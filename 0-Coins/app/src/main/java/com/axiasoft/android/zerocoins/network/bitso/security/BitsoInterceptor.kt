package com.axiasoft.android.zerocoins.network.bitso.security

import okhttp3.Interceptor
import okhttp3.Response

class BitsoInterceptor: Interceptor {
    //TODO for this API an interceptor may not be needed
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request().newBuilder()
        val encodedPath = chain.request().url.encodedPath
        //val x = chain.request().body.toString()
        val response = chain.proceed(originalRequest.build())
        return response
    }
}
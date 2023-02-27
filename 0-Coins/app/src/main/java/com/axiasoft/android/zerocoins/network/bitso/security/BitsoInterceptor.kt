package com.axiasoft.android.zerocoins.network.bitso.security

import com.axiasoft.android.zerocoins.network.bitso.BitsoApiConstParams.AUTH_GEN_HEADER_KEY
import com.axiasoft.android.zerocoins.network.bitso.security.BitsoAuthentication.genAuthHeader
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class BitsoInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request().newBuilder()

        val encodedPath = chain.request().url.encodedPath
        val method = chain.request().method

        val apiCredentialHeader = genAuthHeader(method = method, path = encodedPath)
        // log("z0", "apiCredentialHeader $apiCredentialHeader")

        val requestBuilder: Request.Builder = originalRequest
            .addHeader(AUTH_GEN_HEADER_KEY, apiCredentialHeader)
        // .addHeader("AUTH_GEN_HEADER_KEY", "apiCredentialHeader")
        return chain.proceed(requestBuilder.build())
    }
}

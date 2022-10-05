package com.ari.core.client

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * @author Ari Valencia
 * @file HeadersInterceptor
 * @description Interceptor for add Usser-Agent to all request
 */

class HeadersInterceptor : Interceptor {

    companion object {
        private val USER_AGENT: String =
            "Manufacturer: ${Build.MANUFACTURER} Model: ${Build.MODEL} Hardware: ${Build.HARDWARE} Android_OS: ${Build.VERSION.RELEASE}"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request().newBuilder()
            .header("User-Agent", USER_AGENT)
            .build()

        return chain.proceed(request)
    }
}

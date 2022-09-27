package com.ari.core.client

import android.os.Build
import com.ari.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeadersInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request().newBuilder()
            .header("User-Agent", BuildConfig.LIBRARY_PACKAGE_NAME)
            .header("Device-Manufacturer", Build.MANUFACTURER)
            .header("Device-Model", Build.MODEL)
            .header("Device-Hardware", Build.HARDWARE)
            .build()

        return chain.proceed(request)
    }
}
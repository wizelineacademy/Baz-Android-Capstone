package com.ari.coins.framework.data.network

import com.ari.coins.data.models.CryptoResponse
import com.ari.coins.data.models.Result
import retrofit2.Response

fun <T: CryptoResponse<*>, R> Response<T>.toResult(): Result<R> = try {
    if (isSuccessful) {
        val body = body()!!
        if (body.success) Result.Success(body.payload as R)
        else Result.Error(body.error!!.message, body.error.code)
    } else {
        Result.Error(message(), -1)
    }
} catch (e: Exception) {
    val result: Result<R> = Result.Error(e.toString(), -2)
    result
}
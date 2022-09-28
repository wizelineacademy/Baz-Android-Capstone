package com.ari.coins.framework.data.network

import com.ari.coins.data.models.CryptoResponseData
import com.ari.coins.data.models.ResultData
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException

fun <T: CryptoResponseData<*>, R> Response<T>.toResult(): ResultData<R> = try {
    if (isSuccessful) {
        val body = body()!!
        if (body.success) ResultData.Success(body.payload as R)
        else ResultData.Error(body.error!!.message, body.error.code)
    } else {
        ResultData.Error(message(), -1)
    }
} catch (e: UnknownHostException) {
    val result: ResultData<R> = ResultData.Error(e.toString(), -2)
    result
} catch (e: ConnectException) {
    val result: ResultData<R> = ResultData.Error(e.toString(), -2)
    result
} catch (e: Exception) {
    val result: ResultData<R> = ResultData.Error(e.toString(), -2)
    result
}
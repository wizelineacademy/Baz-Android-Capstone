package com.ari.coins.framework.data.network

import com.ari.coins.data.models.CryptoResponseData
import com.ari.coins.data.models.ResultData
import com.ari.coins.framework.data.network.constants.ErrorCode
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException

suspend fun <R, T: CryptoResponseData<T>> execute(
    task: suspend () -> Response<T>
): ResultData<R> = try {
    val response = task.invoke()
    if (response.isSuccessful) {
        val body = response.body()!!
        if (body.success) ResultData.Success(body.payload as R)
        else ResultData.Error(body.error!!.message, body.error.code)
    } else {
        ResultData.Error(response.message(), ErrorCode.UNKNOWN_LOCAL)
    }
} catch (e: UnknownHostException) {
    ResultData.Error(e.toString(), ErrorCode.UNKNOWN_HOST_EXCEPTION)
} catch (e: ConnectException) {
    ResultData.Error(e.toString(), ErrorCode.CONNECTION_EXCEPTION)
} catch (e: Exception) {
    ResultData.Error(e.toString(), ErrorCode.UNKNOWN)
}
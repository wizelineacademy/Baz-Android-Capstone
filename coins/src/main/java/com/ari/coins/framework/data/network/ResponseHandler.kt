package com.ari.coins.framework.data.network

import com.ari.coins.data.models.CryptoResponseData
import com.ari.coins.data.models.ResultData
import com.ari.coins.framework.data.network.constants.ErrorCode
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * @author        Ari Valencia
 * @file          ResponseHandler
 * @description   Generic function used for extract response of crypto API with Success or Error
 */

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
    ResultData.Error("Sin conexión a internet", ErrorCode.UNKNOWN_HOST_EXCEPTION)
} catch (e: ConnectException) {
    ResultData.Error("Error de conexión", ErrorCode.CONNECTION_EXCEPTION)
} catch (e: Exception) {
    ResultData.Error("Error desconocido", ErrorCode.UNKNOWN)
}
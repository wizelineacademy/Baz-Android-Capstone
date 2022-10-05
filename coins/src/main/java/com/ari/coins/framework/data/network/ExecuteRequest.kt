package com.ari.coins.framework.data.network

import com.ari.coins.data.models.CryptoResponseData
import com.ari.coins.data.models.ResultData
import com.ari.coins.framework.data.network.constants.ErrorCode
import com.ari.coins.framework.data.network.constants.ErrorMessage
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * @author Ari Valencia
 * @file ResponseHandler
 * @description Generic function used for extract response of crypto API with Success or Error
 */

suspend fun <R, T : CryptoResponseData<T>> executeRequest(
    task: suspend () -> Response<T>
): ResultData<R> = try {
    val response = task.invoke()
    if (response.isSuccessful) {
        response.body()?.let { body ->
            if (body.success) ResultData.Success(body.payload as R)
            else ResultData.Error(
                body.error?.message ?: ErrorMessage.UNKNOWN,
                body.error?.code ?: ErrorCode.UNKNOWN
            )
        } ?: run {
            ResultData.Error(response.message(), response.code())
        }
    } else {
        ResultData.Error(response.message(), ErrorCode.UNKNOWN_LOCAL)
    }
} catch (e: UnknownHostException) {
    ResultData.Error(ErrorMessage.UNKNOWN_HOST_EXCEPTION, ErrorCode.UNKNOWN_HOST_EXCEPTION)
} catch (e: ConnectException) {
    ResultData.Error(ErrorMessage.CONNECTION_EXCEPTION, ErrorCode.CONNECTION_EXCEPTION)
} catch (e: Exception) {
    ResultData.Error(ErrorMessage.UNKNOWN, ErrorCode.UNKNOWN)
}

package com.vero.cursowizelinecriptomonedas.api

import com.vero.cursowizelinecriptomonedas.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

suspend fun <T> makeNetworkCall(
    call: suspend () -> T
): ApiResponseStatus<T> = withContext(Dispatchers.IO) {
    try {
        ApiResponseStatus.Success(call())
    } catch (e: UnknownHostException) {
        ApiResponseStatus.Error(R.string.error_unknown_host_exception)
    } catch (e: Exception) {
        ApiResponseStatus.Error(R.string.error_unknown)
    }
}
package com.proyect.cursowizline.api

import com.proyect.cursowizline.R
import com.proyect.cursowizline.model.ResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

suspend fun <T> makeNetworkCall(
    call: suspend () -> T): ResponseStatus<T> = withContext(Dispatchers.IO){
    try {
        ResponseStatus.Success(call())
    } catch (e: UnknownHostException) {
        ResponseStatus.Error(R.string.error_unknown_host_exception)
    } catch (e: Exception) {
        ResponseStatus.Error(R.string.error_unknown)
    }
}
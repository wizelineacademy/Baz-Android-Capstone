package com.baz.cmv.criptomonedas.coins.data.remote.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.UnknownHostException

suspend fun <T> networkResponse(response:suspend () -> T) : ResponseStatus <T> = withContext(Dispatchers.IO){
    try {
        ResponseStatus.Succes(response())
    }catch (e: UnknownHostException){
        ResponseStatus.Error("Error de host")
    }catch (e: Exception){
        ResponseStatus.Error("Error desconocido ")
    }
}


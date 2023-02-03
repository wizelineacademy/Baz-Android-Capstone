package com.baz.cmv.criptomonedas.coins.data.remote.network

import com.baz.cmv.criptomonedas.coins.data.remote.response.NetworkObtenerMonedas
import com.baz.cmv.criptomonedas.coins.core.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoinsService {

    private val retrofit = RetrofitService.getRetrofitInstance()
    suspend fun getMonedas(): NetworkObtenerMonedas {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CoinsApiClient::class.java).obtenerMonedas()
            response
        }
    }
}
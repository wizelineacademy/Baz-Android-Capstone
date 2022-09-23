package com.baz.cmv.criptomonedas.coins.service

import com.baz.cmv.criptomonedas.coins.model.remote.response.NetworkObtenerMonedas
import retrofit2.http.GET

private const val GET_COINS_PATH = "v3/available_books"
interface CoinsService {
    @GET(GET_COINS_PATH)
    suspend fun obtenerMonedas(): NetworkObtenerMonedas

}
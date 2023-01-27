package com.baz.cmv.criptomonedas.coins.data.remote.network

import com.baz.cmv.criptomonedas.coins.data.remote.response.NetworkMoneda
import com.baz.cmv.criptomonedas.coins.data.remote.response.NetworkObtenerMonedas
import retrofit2.http.GET
import retrofit2.http.Query

private const val GET_COINS_PATH = "v3/available_books"
private const val GET_ORDER_PATH = "v3/order_book/"
interface CoinsApiClient {
    @GET(GET_COINS_PATH)
    suspend fun obtenerMonedas(): NetworkObtenerMonedas
    @GET(GET_ORDER_PATH)
    suspend fun obtenerDetalle(@Query("book") book:String): NetworkMoneda


}




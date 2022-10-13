package com.baz.cmv.criptomonedas.coins.data.remote.response


import com.google.gson.annotations.SerializedName

data class NetworkObtenerMonedas(
    @SerializedName("payload")
    val monedas: List<NetworkMoneda>,
    @SerializedName("success")
    val success: Boolean
)
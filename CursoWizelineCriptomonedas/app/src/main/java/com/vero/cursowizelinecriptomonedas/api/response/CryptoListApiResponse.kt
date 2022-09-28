package com.vero.cursowizelinecriptomonedas.api.response

import com.squareup.moshi.Json
import com.vero.cursowizelinecriptomonedas.api.dto.CryptoDTO

class CryptoListApiResponse(
    @field: Json(name = "success")
    val isSuccess: Boolean,
    @field: Json(name = "payload")
    val payload: List<CryptoDTO>,
)
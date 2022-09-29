package com.proyect.cursowizline.model

import com.squareup.moshi.Json

class CryptoListResponse(
    @field: Json(name = "payload")
    val payload: List<CryptoDTO>,
)
package com.vero.cursowizelinecriptomonedas.api.response

import com.vero.cursowizelinecriptomonedas.model.CryptoOrderPayload

data class CryptoOrderListApiResponse(
    val payload: CryptoOrderPayload,
    val success: Boolean
)
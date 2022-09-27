package com.ari.coins.data.models

/**
 * Generic class for any response of Bitso-API
 */
data class CryptoResponseData<T>(
    val success: Boolean,
    val error: CryptoErrorData?,
    val payload: T?
)

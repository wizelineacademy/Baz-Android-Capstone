package com.ari.coins.ui.uiModels

/**
 * Generic class for any response of Bitso-API
 */
data class CryptoResponse<T>(
    val success: Boolean,
    val error: CryptoError?,
    val payload: T?
)

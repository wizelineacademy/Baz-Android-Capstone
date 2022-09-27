package com.ari.coins.domain.domainModels

import com.ari.coins.data.models.CryptoResponseData

/**
 * Generic class for any response of Bitso-API
 */
data class CryptoResponseDomain<T>(
    val success: Boolean,
    val error: CryptoErrorDomain?,
    val payload: T?
)

fun <T> CryptoResponseData<T>.toDomain() = CryptoResponseDomain<T>(
    success = success,
    error = error?.toDomain(),
    payload = payload
)

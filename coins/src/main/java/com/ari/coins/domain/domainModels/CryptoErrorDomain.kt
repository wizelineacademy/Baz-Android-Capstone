package com.ari.coins.domain.domainModels

import com.ari.coins.data.models.CryptoErrorData

data class CryptoErrorDomain(
    val message: String,
    val code: Int
)

fun CryptoErrorData.toDomain() = CryptoErrorDomain(message, code)
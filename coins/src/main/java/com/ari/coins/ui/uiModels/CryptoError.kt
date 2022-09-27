package com.ari.coins.ui.uiModels

import com.ari.coins.domain.domainModels.CryptoErrorDomain

data class CryptoError(
    val message: String,
    val code: Int
)

fun CryptoErrorDomain.toUi() = CryptoError(message, code)
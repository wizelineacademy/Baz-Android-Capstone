package com.ari.coins.ui.uiModels

import com.ari.coins.domain.domainModels.FlatRateDomain

data class FlatRate(
    val maker: String,
    val taker: String
)

fun FlatRateDomain.toUi() = FlatRate(maker, taker)

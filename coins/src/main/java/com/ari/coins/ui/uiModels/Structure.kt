package com.ari.coins.ui.uiModels

import com.ari.coins.domain.domainModels.StructureDomain

data class Structure(
    val maker: String,
    val taker: String,
    val volume: String
)

fun StructureDomain.toUi() = Structure(maker, taker, volume)

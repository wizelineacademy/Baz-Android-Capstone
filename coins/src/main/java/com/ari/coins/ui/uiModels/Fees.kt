package com.ari.coins.ui.uiModels

import com.ari.coins.domain.domainModels.FeesDomain

data class Fees(
    val flatRate: FlatRate,
    val structure: List<Structure>
)

fun FeesDomain.toUi() = Fees(
    flatRate = flatRate.toUi(),
    structure = structure.map { it.toUi() }
)

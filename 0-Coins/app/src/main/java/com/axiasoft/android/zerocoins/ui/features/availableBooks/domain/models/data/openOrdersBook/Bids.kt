package com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.openOrdersBook

data class Bids(
    override var book: String?,
    override var price: String?,
    override var amount: String?,
) : OpenOrder

package com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book

data class Bids(
    override var book: String?,
    override var price: String?,
    override var amount: String?
) : OpenOrder

package com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book

data class Ask(
    override var book: String?,
    override var price: String?,
    override var amount: String?,
) : OpenOrder {
    fun sumAmount(a: Int, b: Int): Int {
        return a + b
    }

    fun multplicateAmount(a: Double, b: Double): Double {
        return a * b
    }
}

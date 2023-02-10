package com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book

data class Ask (
    var book          : String? = null,
    var price         : String? = null,
    var amount        : String? = null,
){
    fun sumAmount(a: Int, b: Int): Int{
        return a + b
    }

    fun multplicateAmount(a: Double, b: Double): Double{
        return a * b
    }
}
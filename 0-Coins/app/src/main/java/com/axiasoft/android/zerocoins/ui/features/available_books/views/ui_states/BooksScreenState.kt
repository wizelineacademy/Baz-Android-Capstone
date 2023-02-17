package com.axiasoft.android.zerocoins.ui.features.available_books.views.ui_states

import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.ExchangeOrderBook

sealed class BooksScreenState() {
    data class BooksSuccess(val data: ArrayList<ExchangeOrderBook>): BooksScreenState()
    data class BooksErrorOrEmpty(val message: String? = null): BooksScreenState()
    class Loading(): BooksScreenState()
}
package com.axiasoft.android.zerocoins.ui.features.availableBooks.views.uiStates

import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.exchange_order_book.ExchangeOrderBook

sealed class BooksScreenState() {
    data class BooksSuccess(val data: ArrayList<ExchangeOrderBook>) : BooksScreenState()
    data class BooksErrorOrEmpty(val message: String? = null) : BooksScreenState()
}

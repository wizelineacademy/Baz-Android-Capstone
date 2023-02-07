package com.axiasoft.android.zerocoins.ui.features.available_books.views.ui_states

import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.order_book.response.Ask
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.order_book.response.Bids

sealed class ListOrderBookScreenState {
    data class Success(val asks: ArrayList<Ask>, val bids: ArrayList<Bids>) : ListOrderBookScreenState()
    data class ErrorOrEmpty(val message: String? = null): ListOrderBookScreenState()
}
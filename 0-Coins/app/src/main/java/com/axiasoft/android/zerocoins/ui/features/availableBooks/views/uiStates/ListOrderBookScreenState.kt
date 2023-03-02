package com.axiasoft.android.zerocoins.ui.features.availableBooks.views.uiStates

import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.openOrdersBook.Ask
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.openOrdersBook.Bids

sealed class ListOrderBookScreenState {
    data class Success(val asks: ArrayList<Ask>, val bids: ArrayList<Bids>) : ListOrderBookScreenState()
    data class ErrorOrEmpty(val message: String? = null) : ListOrderBookScreenState()
}

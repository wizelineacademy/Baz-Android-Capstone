package com.axiasoft.android.zerocoins.ui.features.available_books.views.ui_states

import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.ticker.Ticker

sealed class TickerScreenState {
    data class TickerSuccess(val ticker: Ticker): TickerScreenState()
    data class TickerError(val message: String? = null): TickerScreenState()
}
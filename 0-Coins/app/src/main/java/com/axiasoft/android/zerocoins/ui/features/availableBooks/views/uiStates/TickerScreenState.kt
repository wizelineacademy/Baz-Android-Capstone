package com.axiasoft.android.zerocoins.ui.features.availableBooks.views.uiStates

import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.ticker.Ticker

sealed class TickerScreenState {
    data class TickerSuccess(val ticker: Ticker) : TickerScreenState()
    data class TickerError(val message: String? = null) : TickerScreenState()
}

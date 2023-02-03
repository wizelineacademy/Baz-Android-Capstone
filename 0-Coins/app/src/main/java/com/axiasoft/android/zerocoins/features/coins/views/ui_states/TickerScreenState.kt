package com.axiasoft.android.zerocoins.features.coins.views.ui_states

import com.axiasoft.android.zerocoins.features.coins.domain.models.data.ticker.response.Ticker

sealed class TickerScreenState {
    data class TickerSuccess(val ticker: Ticker): TickerScreenState()
    data class TickerError(val message: String? = null): TickerScreenState()
}
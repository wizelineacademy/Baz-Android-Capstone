package com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.ticker

data class Ticker(
    var book: String? = null,
    var high: String? = null,
    var last: String? = null,
    var createdAt: String? = null,
    var volume: String? = null,
    var vwap: String? = null,
    var low: String? = null,
    var ask: String? = null,
    var bid: String? = null,
    var change24: String? = null,
    var rollingAverageChange: HashMap<String, String>? = null
)

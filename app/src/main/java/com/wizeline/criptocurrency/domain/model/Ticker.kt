package com.wizeline.criptocurrency.domain.model

import java.util.*

data class Ticker (
    var book: String? = null,
    var volume: String? = null,
    var high: String? = null,
    var last: String? = null,
    var low: String? = null,
    var vwap: String? = null,
    var ask: String? = null,
    var bid: String? = null,
    var created_at: Date? = null,
    var change_24: String? = null,
    var rolling_average_change: Any? = null
)
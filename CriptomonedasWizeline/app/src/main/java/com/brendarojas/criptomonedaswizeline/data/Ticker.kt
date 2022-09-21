package com.brendarojas.criptomonedaswizeline.data

import com.google.gson.annotations.SerializedName

data class Ticker(
    var high: String,
    var last: String,
    var book: String,
    var volume: String,
    var vwap: String,
    var low: String,
    var ask: String,
    var bid: String,
    @SerializedName("created_at") var createdAt: String,
    @SerializedName("achange_24sk") var achange_24sk: String
)
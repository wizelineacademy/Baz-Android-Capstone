package com.javg.cryptocurrencies.data.model

data class CRYBook(
    var book: String,
    var imageUrl: String
)

data class CRYDetailBook(
    var high: String = "",
    var last: String = "",
    var low: String = "",
    var askList: List<CRYAskOrBids>? = null,
    var bidsList: List<CRYAskOrBids>? = null
)
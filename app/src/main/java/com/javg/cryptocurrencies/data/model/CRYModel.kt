package com.javg.cryptocurrencies.data.model

import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName

data class CRYBook(
    var book: String,
    @DrawableRes var image: Int
)

data class CRYDetailBook(
    var high: String = "",
    var last: String = "",
    var low: String = "",
    var askList: List<CRYAskOrBids>? = null,
    var bidsList: List<CRYAskOrBids>? = null
)
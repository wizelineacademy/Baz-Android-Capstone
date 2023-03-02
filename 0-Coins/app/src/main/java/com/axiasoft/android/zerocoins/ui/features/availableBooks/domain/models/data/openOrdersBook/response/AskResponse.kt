package com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.openOrdersBook.response

import com.google.gson.annotations.SerializedName

data class AskResponse(
    @SerializedName("book") var book: String? = null,
    @SerializedName("price") var price: String? = null,
    @SerializedName("amount") var amount: String? = null,
)

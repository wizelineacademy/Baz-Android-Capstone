package com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.open_orders_book.response

import com.google.gson.annotations.SerializedName

data class AskResponse(
    @SerializedName("book") var book: String? = null,
    @SerializedName("price") var price: String? = null,
    @SerializedName("amount") var amount: String? = null,
)

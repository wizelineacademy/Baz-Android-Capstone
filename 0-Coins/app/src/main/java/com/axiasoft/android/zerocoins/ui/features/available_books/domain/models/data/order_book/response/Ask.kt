package com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.order_book.response

import com.google.gson.annotations.SerializedName

data class Ask (
    @SerializedName("book"  ) var book          : String? = null,
    @SerializedName("price" ) var price         : String? = null,
    @SerializedName("amount") var amount        : String? = null,
)
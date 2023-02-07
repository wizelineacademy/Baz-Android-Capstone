package com.axiasoft.android.zerocoins.features.available_books.domain.models.data.order_book.response

import com.google.gson.annotations.SerializedName

data class ListOrderBookResponse (
    @SerializedName("asks"  ) var asks          : ArrayList<Ask>? = null,
    @SerializedName("bids"  ) var bids          : ArrayList<Bids>? = null
)
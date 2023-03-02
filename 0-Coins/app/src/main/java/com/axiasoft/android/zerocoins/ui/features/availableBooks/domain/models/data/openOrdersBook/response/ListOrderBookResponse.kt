package com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.openOrdersBook.response

import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.openOrdersBook.Ask
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.openOrdersBook.Bids
import com.google.gson.annotations.SerializedName

data class ListOrderBookResponse(
    @SerializedName("asks") var asks: ArrayList<Ask>? = null,
    @SerializedName("bids") var bids: ArrayList<Bids>? = null,
)

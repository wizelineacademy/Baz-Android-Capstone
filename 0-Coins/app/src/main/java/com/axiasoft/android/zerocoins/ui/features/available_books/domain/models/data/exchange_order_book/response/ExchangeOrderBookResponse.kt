package com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.response

import com.google.gson.annotations.SerializedName

data class ExchangeOrderBookResponse (

    @SerializedName("book"           ) var book          : String? = null,
    @SerializedName("minimum_amount" ) var minimumAmount : String? = null,
    @SerializedName("maximum_amount" ) var maximumAmount : String? = null,
    @SerializedName("minimum_price"  ) var minimumPrice  : String? = null,
    @SerializedName("maximum_price"  ) var maximumPrice  : String? = null,
    @SerializedName("minimum_value"  ) var minimumValue  : String? = null,
    @SerializedName("maximum_value"  ) var maximumValue  : String? = null
)
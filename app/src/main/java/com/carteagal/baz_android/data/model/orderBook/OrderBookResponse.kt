package com.carteagal.baz_android.data.model.orderBook

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OrderBookResponse(
    @Expose
    @SerializedName("updated_at") val updatedAt: String?,
    @Expose
    @SerializedName("sequence") val sequence: String?,
    @Expose
    @SerializedName("binds") val binds: List<BindsResponse>?
)

data class BindsResponse(
    @Expose
    @SerializedName("book") val book: String?,
    @Expose
    @SerializedName("price") val price: String?,
    @Expose
    @SerializedName("amount") val amount: String?
)
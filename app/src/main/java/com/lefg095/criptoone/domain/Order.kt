package com.lefg095.criptoone.domain


import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("asks")
    var asks: List<Ask>,
    @SerializedName("bids")
    var bids: List<Bid>,
    @SerializedName("sequence")
    var sequence: String,
    @SerializedName("updated_at")
    var updatedAt: String
)
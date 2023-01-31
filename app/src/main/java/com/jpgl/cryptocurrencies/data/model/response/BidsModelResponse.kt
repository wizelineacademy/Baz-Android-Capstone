package com.jpgl.cryptocurrencies.data.model.response


import com.google.gson.annotations.SerializedName

data class BidsModelResponse(
    var success: Boolean,
    @SerializedName("payload") var bidsResponse: PayloadBidsModelResponse
)
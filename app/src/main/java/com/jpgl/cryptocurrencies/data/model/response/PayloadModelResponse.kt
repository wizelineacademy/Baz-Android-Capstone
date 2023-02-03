package com.jpgl.cryptocurrencies.data.model.response

import com.jpgl.cryptocurrencies.data.model.BidsModel
import com.jpgl.cryptocurrencies.data.model.AsksModel
import com.google.gson.annotations.SerializedName

data class PayloadBidsModelResponse(
    @SerializedName("updated_at") var updatedAt: String,        //Timestamp at which the order was last updated
    var sequence: String,                                       //Increasing integer value for each order book update.
    @SerializedName("bids") var dataBids: ArrayList<BidsModel>, //data bids
    @SerializedName("asks") var dataAsks: ArrayList<AsksModel>   //data
)
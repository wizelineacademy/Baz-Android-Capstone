package com.brendarojas.criptomonedaswizeline.data

import com.google.gson.annotations.SerializedName

data class PayloadBidsResponse (
    @SerializedName("updated_at") var updatedAt: String,     //Timestamp at which the order was last updated
    var sequence: String,                                   //Increasing integer value for each order book update.
    var bids: ArrayList<Bids>                               //List of open bids
)
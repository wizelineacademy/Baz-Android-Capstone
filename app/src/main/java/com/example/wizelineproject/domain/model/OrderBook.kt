package com.example.wizelineproject.domain.model

import com.google.gson.annotations.SerializedName

data class OrderBook(@SerializedName("bids")
                     val bids:List<Transaction>?,
                     @SerializedName("asks")
                     val asks:List<Transaction>?,
                     @SerializedName("updated_at")
                     val updateAt:String,
                     @SerializedName("sequence")
                     val sequence:String
)
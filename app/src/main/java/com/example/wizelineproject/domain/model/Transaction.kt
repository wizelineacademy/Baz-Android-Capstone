package com.example.wizelineproject.domain.model

import com.google.gson.annotations.SerializedName

//For Bid and Ask
data class Transaction(@SerializedName("book")
                       val book:String,
                       @SerializedName("price")
                       val price:String,
                       @SerializedName("amount")
                       val amount:String
)

package com.example.wizelineproject.domain.model

import com.google.gson.annotations.SerializedName

data class Ticker(
    @SerializedName("book")
    val book:String,
    @SerializedName("volume")
    val volume:String,
    @SerializedName("high")
    val high:String,
    @SerializedName("last")
    val last:String,
    @SerializedName("low")
    val low:String,
    @SerializedName("vwap")
    val vwap:String,
    @SerializedName("ask")
    val ask:String,
    @SerializedName("bid")
    val bid:String,
    @SerializedName("created_at")
    val createAt:String
){
    override fun toString(): String {
        return "["+book+", \n"+volume+", \n"+high+", \n"+last+", \n"+low+", \n"+vwap+", \n"+ask+", \n"+bid+", \n"+createAt+"]"

    }
}
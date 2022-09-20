package com.brendarojas.criptomonedaswizeline.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Book(
    @SerializedName("book") var book: String,                       //Order book symbol
    @SerializedName("minimum_price") val minimum_price: String,     //Minimum price when placing orders
    @SerializedName("maximum_price") val maximum_price: String,     //Maximum price when placing orders
    @SerializedName("minimum_amount") val minimum_amount: String,   //Minimum amount of major when placing orders
    @SerializedName("maximum_amount") val maximum_amount: String,   //Maximum amount of major when placing orders
    @SerializedName("minimum_value") val minimum_value: String,     //Minimum value amount (amount*price) when placing orders
    @SerializedName("maximum_value") val maximum_value: String      //Maximum value amount (amount*price) when placing orders
) : Serializable
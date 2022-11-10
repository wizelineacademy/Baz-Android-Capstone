package com.lefg095.criptoone.domain

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
open class Book(
    @Expose
    @SerializedName("book")
    var book: String = "",
    @Expose
    @SerializedName("minimum_amount")
    var minimum_amount: String = "",
    @Expose
    @SerializedName("maximum_amount")
    var maximum_amount: String = "",
    @Expose
    @SerializedName("minimum_price")
    var minimum_price: String = "",
    @Expose
    @SerializedName("maximum_price")
    var maximum_price: String = "",
    @Expose
    @SerializedName("minimum_value")
    var minimum_value: String = "",
    @Expose
    @SerializedName("maximum_value")
    var maximum_value: String = ""
): Parcelable
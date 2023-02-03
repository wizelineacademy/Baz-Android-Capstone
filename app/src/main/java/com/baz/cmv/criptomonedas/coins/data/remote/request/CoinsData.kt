package com.baz.cmv.criptomonedas.coins.data.remote.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class CoinsData(val book: String,
                     val maximumAmount: String,
                     val maximum_price: String,
                     val maximum_value: String,
                     val minimum_amount: String,
                     val minimum_price: String,
                     val minimum_value: String) : Parcelable {

}
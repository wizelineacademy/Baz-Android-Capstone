package com.javg.cryptocurrencies.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class CRYBaseResponse<T: Any>{
    @SerializedName("success")
    @Expose
    var success: Boolean = false
    @SerializedName("payload")
    @Expose
    var payload: T? = null
}

@Entity
data class CRYBookResponse(
    @PrimaryKey
    @SerializedName("book")
    @ColumnInfo(name = "book") var book: String,
    @SerializedName("minimum_price")
    @ColumnInfo(name = "minimumPrice") var minimumPrice: String,
    @SerializedName("maximum_price")
    var maximumPrice: String,
    @SerializedName("minimum_amount")
    var minimumAmount: String,
    @SerializedName("maximum_amount")
    var maximumAmount: String,
    @SerializedName("minimum_value")
    var minimumValue: String,
    @SerializedName("maximum_value")
    var maximumValue: String,
    @SerializedName("tick_size")
    var tickSize: String,
    @SerializedName("default_chart")
    var defaultChart: String
)

data class CRYTicker(
    @SerializedName("book")
    var book: String,
    @SerializedName("volume")
    var volume: String,
    @SerializedName("high")
    var high: String,
    @SerializedName("last")
    var last: String,
    @SerializedName("low")
    var low: String,
    @SerializedName("vwap")
    var vwap: String,
    @SerializedName("ask")
    var ask: String,
    @SerializedName("bid")
    var bid: String
)

data class CRYOrderBook(
    @SerializedName("bids")
    var bidsList: List<CRYAskOrBids>,
    @SerializedName("asks")
    var asksList:  List<CRYAskOrBids>
)

data class CRYAskOrBids(
    @SerializedName("book")
    var book: String,
    @SerializedName("price")
    var price: String,
    @SerializedName("amount")
    var amount: String
)
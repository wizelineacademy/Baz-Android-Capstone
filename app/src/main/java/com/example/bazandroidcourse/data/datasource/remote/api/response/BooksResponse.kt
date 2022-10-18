package com.example.bazandroidcourse.data.datasource.remote.api.response

import com.google.gson.annotations.SerializedName

data class BooksResponse(
    var success: Boolean? = null,
    var payload: List<Any> = emptyList()
) {
    data class Book (
        var book: String? = null,
       @SerializedName ("minimum_amount") var minimumAmount :String? = null, //": ".003",
       @SerializedName ("maximum_amount") var maximumAmount :String? = null, //": "1000.00",
       @SerializedName ("minimum_price")  var minimunPrice  :String? = null, //: "100.0",
       @SerializedName ("maximum_price")  var maximumPrice  :String? = null, //: "1000000.0",
       @SerializedName ("minimum_value")  var minimumValue  :String? = null, //: "25.0",
       @SerializedName ("maximum_value")  var maximumValue  :String? = null, //: "1000000.0"
    )
}
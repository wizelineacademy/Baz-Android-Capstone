package com.example.bazandroidcourse.data.datasource.remote.api.response

import com.google.gson.annotations.SerializedName

data class BooksResponse(
    var success: Boolean? = null,
    var payload: List<Book> = emptyList()
)

data class Book (
    var book: String? = null,
    @SerializedName ("minimum_amount") var minimumAmount :String? = null,
    @SerializedName ("maximum_amount") var maximumAmount :String? = null,
    @SerializedName ("minimum_price")  var minimumPrice  :String? = null,
    @SerializedName ("maximum_price")  var maximumPrice  :String? = null,
    @SerializedName ("minimum_value")  var minimumValue  :String? = null,
    @SerializedName ("maximum_value")  var maximumValue  :String? = null,
)
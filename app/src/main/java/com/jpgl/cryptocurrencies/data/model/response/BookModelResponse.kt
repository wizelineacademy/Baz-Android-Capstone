package com.jpgl.cryptocurrencies.data.model.response

import com.jpgl.cryptocurrencies.data.model.BookModel
import com.google.gson.annotations.SerializedName

data class BookModelResponse (
    var success: Boolean,
    @SerializedName("payload") var bookData: ArrayList<BookModel>
)
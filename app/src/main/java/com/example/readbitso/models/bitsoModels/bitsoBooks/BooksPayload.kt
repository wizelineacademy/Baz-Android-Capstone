package com.example.readbitso.models.bitsoModels.bitsoBooks

import com.google.gson.annotations.SerializedName

data class BooksPayload(
    val id: Int = 0,
    @SerializedName("book") val book: String,
    @SerializedName("maximum_price")val maximumPrice: String,
    @SerializedName("minimum_price")val minimumPrice: String,
)

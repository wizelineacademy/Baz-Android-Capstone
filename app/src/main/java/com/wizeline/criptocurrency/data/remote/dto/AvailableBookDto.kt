package com.wizeline.criptocurrency.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.wizeline.criptocurrency.domain.model.AvailableBook

data class AvailableBookDto(
    @SerializedName("book")
    @Expose
    var book: String? = null,
    @SerializedName("minimum_value")
    @Expose
    var minimum_value: String? = null,
    @SerializedName("maximum_value")
    @Expose
    var maximum_value: String? = null
) {
    fun toAvailableBook(): AvailableBook =
        AvailableBook(
            book = this.book,
            minimum_value = this.minimum_value,
            maximum_value = this.maximum_value
        )
}

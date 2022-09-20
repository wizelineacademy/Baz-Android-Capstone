package com.brendarojas.criptomonedaswizeline.models

import com.google.gson.annotations.SerializedName

data class AvailableBooks (
    @SerializedName("success") var success: Boolean,
    @SerializedName("payload") var payload: ArrayList<Book>
)
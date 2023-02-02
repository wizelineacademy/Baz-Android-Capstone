package com.carteagal.baz_android.data.model.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorBase(
    @Expose
    @SerializedName("code") val code: String,

    @Expose
    @SerializedName("mesage") val message: String
)
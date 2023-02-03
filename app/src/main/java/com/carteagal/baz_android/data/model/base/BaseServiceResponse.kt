package com.carteagal.baz_android.data.model.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseServiceResponse<T>(
    @Expose
    @SerializedName("success") val success: Boolean,

    @Expose
    @SerializedName("payload") val result: T? = null,

    @Expose
    @SerializedName("error") val error: ErrorBase? = null
)
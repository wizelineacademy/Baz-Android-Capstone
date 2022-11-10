package com.lefg095.criptoone.domain.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @Expose
    @SerializedName("success")
    var success: String = "",
    @Expose
    @SerializedName("payload")
    var payload: List<T> = listOf()
)
package com.example.wizelineproject.domain.response

import com.google.gson.annotations.SerializedName

data class GenericObjectResponse<T>(
    @SerializedName("success")
    val success:Boolean?,
    @SerializedName("payload")
    val payload:T?
)

package com.example.wizelineproject.domain.response

import com.example.wizelineproject.domain.model.Book
import com.google.gson.annotations.SerializedName

data class GenericArrayResponse<T>(
    @SerializedName("success")
    val success:Boolean?,
    @SerializedName("payload")
    val payload:List<T>?
    )

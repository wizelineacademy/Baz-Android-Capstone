package com.example.criptobitsoproyectwz.data.model

import com.google.gson.annotations.SerializedName

data class BaseResult(
@SerializedName("success") val exitoso: Boolean,
@SerializedName("payload") val payload: List<Payload>
)

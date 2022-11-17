package com.wizeline.criptocurrency.data.remote.dto.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.wizeline.criptocurrency.data.remote.dto.TickerDto
import java.io.Serializable

data class TickerResponse(
    @SerializedName("success")
    @Expose
    var success: Boolean? = null,
    @SerializedName("payload")
    @Expose
    var payload: TickerDto? = null
)

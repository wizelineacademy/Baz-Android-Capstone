package com.wizeline.criptocurrency.data.remote.dto.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.wizeline.criptocurrency.data.remote.dto.AvailableBookDto
import java.io.Serializable

data class AvailableBooksResponse (
    @SerializedName("success")
    @Expose
    var success: Boolean? = null,
    @SerializedName("payload")
    @Expose
    var payload: List<AvailableBookDto>? = null
)
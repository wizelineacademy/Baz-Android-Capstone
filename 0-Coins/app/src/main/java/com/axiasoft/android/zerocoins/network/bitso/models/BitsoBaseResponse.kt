package com.axiasoft.android.zerocoins.network.bitso.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BitsoBaseResponse<T> (
    // Response OK
    @SerializedName("success") @Expose
    val success: Boolean?,
    @SerializedName("payload") @Expose
    val payload: T? = null,
    // Response Error
    @SerializedName("error") @Expose
    val error: BitsoErrorResponse? = null
)

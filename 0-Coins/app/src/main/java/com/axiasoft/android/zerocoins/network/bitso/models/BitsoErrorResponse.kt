package com.axiasoft.android.zerocoins.network.bitso.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BitsoErrorResponse(
    @SerializedName("message") @Expose val message: String?,
    @SerializedName("code") @Expose val code: Int?,
)

package com.axiasoft.android.zerocoins.network.bitso.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NetworkError(

    @SerializedName("code")
    @Expose
    var code: Int? = null,

    @SerializedName("error_body")
    @Expose
    var errorBody: String? = null,
)

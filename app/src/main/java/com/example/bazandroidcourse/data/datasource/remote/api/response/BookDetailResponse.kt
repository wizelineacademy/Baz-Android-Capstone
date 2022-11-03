package com.example.bazandroidcourse.data.datasource.remote.api.response
import com.google.gson.annotations.SerializedName

data class BookDetailResponse(
    var success: Boolean? = null,
    var payload: BookDetailResponse
) {
    data class BookDetailResponse(
        var book    : String? = null, //: "btc_mxn",
        var volume  : String? = null, //: "22.31349615",
        var high    : String? = null, //: "5750.00",
        var last    : String? = null, //: "5633.98",
        var low     : String? = null, //: "5450.00",
    )
}

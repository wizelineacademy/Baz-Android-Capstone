package com.example.bazandroidcourse.data.datasource.remote.api.response

import com.google.gson.annotations.SerializedName

data class TickerInfoResponse(
    var success: Boolean? = null,
    var payload: TickerInfo
) {
    data class TickerInfo(
        var book: String? = null, //: "btc_mxn",
        var volume: String? = null, //: "22.31349615",
        var high: String? = null, //: "5750.00",
        var last: String? = null, //: "5633.98",
        var low: String? = null, //: "5450.00",
        var vwap: String? = null, //: "5393.45",
        var ask: String? = null, //: "5632.24",
        var bid: String? = null, //: "5520.01",
        var create: String? = null, //: "2016-04-08T17:52:31.000+00:00"
    )
}

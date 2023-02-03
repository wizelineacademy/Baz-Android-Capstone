package wizeline.crypto.currency.data.models

import com.google.gson.annotations.SerializedName

data class PayloadTickerDto (
    val book  :    String?=null,
    val volume:   String?=null,
    val high  :    String?=null,
    val last  :    String?=null,
    val low   :     String?=null,
    @SerializedName("vwap")
    val wap  :    String?=null,
    val ask   :     String?=null,
    val bid   :     String?=null,
    val created_at: String?=null
)
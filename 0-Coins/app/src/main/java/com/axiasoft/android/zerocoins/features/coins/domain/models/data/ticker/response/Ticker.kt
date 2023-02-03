package com.axiasoft.android.zerocoins.features.coins.domain.models.data.ticker.response

import com.google.gson.annotations.SerializedName


data class Ticker (

  @SerializedName("high"                   ) var high                 : String?               = null,
  @SerializedName("last"                   ) var last                 : String?               = null,
  @SerializedName("created_at"             ) var createdAt            : String?               = null,
  @SerializedName("book"                   ) var book                 : String?               = null,
  @SerializedName("volume"                 ) var volume               : String?               = null,
  @SerializedName("vwap"                   ) var vwap                 : String?               = null,
  @SerializedName("low"                    ) var low                  : String?               = null,
  @SerializedName("ask"                    ) var ask                  : String?               = null,
  @SerializedName("bid"                    ) var bid                  : String?               = null,
  @SerializedName("change_24"              ) var change24             : String?               = null,
  @SerializedName("rolling_average_change" ) var rollingAverageChange : HashMap<String, String>? = null

)
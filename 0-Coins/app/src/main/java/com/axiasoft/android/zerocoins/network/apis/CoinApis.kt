package com.axiasoft.android.zerocoins.network.apis

import com.axiasoft.android.zerocoins.BuildConfig

enum class CoinApis(var hostUrl: String) {
    BITSO(BuildConfig.BITSO_HOST),
    RESTFUL_API(BuildConfig.RESTFUL_API),
    OTHERS(BuildConfig.BITSO_HOST),
}

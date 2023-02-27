package com.axiasoft.android.zerocoins.common

import android.util.Log
import com.axiasoft.android.zerocoins.BuildConfig

fun log(tag: String = "0-Coins", message: String) {
    if (BuildConfig.DEBUG) {
        Log.d(tag, message)
    }
}

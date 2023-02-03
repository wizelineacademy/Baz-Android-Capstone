package com.axiasoft.android.zerocoins.network.bitso.wrappers

import com.axiasoft.android.zerocoins.network.bitso.models.NetworkError

sealed class BitsoApiResponseWrap<out T> {

    data class Success<out T>(val response: T): BitsoApiResponseWrap<T>()

    //TODO Bitso error?

    data class NetworkError(
        val code: Int? = null,
        val error: com.axiasoft.android.zerocoins.network.bitso.models.NetworkError? = null
    ): BitsoApiResponseWrap<Nothing>()

    //object NetworkError: BitsoApiResponseWrap<Nothing>()

}
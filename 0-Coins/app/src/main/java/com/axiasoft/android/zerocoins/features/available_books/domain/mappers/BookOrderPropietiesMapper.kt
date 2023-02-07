package com.axiasoft.android.zerocoins.features.available_books.domain.mappers

import com.axiasoft.android.zerocoins.R

fun bookOrderName(bookOrderCode: String): String{
    return ""
}

fun bookOrderImageResId(bookOrderCode: String): Int{
    return R.drawable.ic_no_image
}

fun getBookOrderType(bookOrderCode: String): CoinNameAndImage? {
    return CoinNameAndImage.values().firstOrNull {it.coinKey == bookOrderCode}
}


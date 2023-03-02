package com.axiasoft.android.zerocoins.ui.features.available_books.domain.mappers

fun getBookOrderType(bookOrderCode: String): CoinNameAndImage? {
    return CoinNameAndImage.values().firstOrNull { it.coinKey == bookOrderCode }
}

fun getCryptoCoinUI(orderBookKey: String): CryptoCoinUI? {
    return CryptoCoinUI.values().firstOrNull { it.coinKey == orderBookKey }
}

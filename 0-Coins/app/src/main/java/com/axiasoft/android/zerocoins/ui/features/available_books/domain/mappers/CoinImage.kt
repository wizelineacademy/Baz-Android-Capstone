package com.axiasoft.android.zerocoins.ui.features.available_books.domain.mappers

import com.axiasoft.android.zerocoins.R
import com.axiasoft.android.zerocoins.application.ZeroCoinsApplication
import java.util.*

enum class CoinNameAndImage(val coinName: String, val coinImage: Int, val coinKey: String) {

    btc_mxn(coinName = getStringRes(R.string.book_order_name_btc_mxn), coinImage = R.drawable.ic_btc, coinKey = "btc_mxn"),
    eth_btc(coinName = getStringRes(R.string.book_order_name_eth_btc), coinImage = R.drawable.ic_ethereum, coinKey = "eth_btc"),
    eth_mxn(coinName = getStringRes(R.string.book_order_name_eth_mxn), coinImage = R.drawable.ic_ethereum, coinKey = "eth_mxn"),
    xrp_btc(coinName = getStringRes(R.string.book_order_name_xrp_btc), coinImage = R.drawable.ic_xrp, coinKey = "xrp_btc"),
    xrp_mxn(coinName = getStringRes(R.string.book_order_name_xrp_mxn), coinImage = R.drawable.ic_xrp, coinKey = "xrp_mxn"),
    ltc_btc(coinName = getStringRes(R.string.book_order_name_ltc_btc), coinImage = R.drawable.ic_litecoin, coinKey = "ltc_btc"),
    ltc_mxn(coinName = getStringRes(R.string.book_order_name_ltc_mxn), coinImage = R.drawable.ic_litecoin, coinKey = "ltc_mxn"),
    bch_btc(coinName = getStringRes(R.string.book_order_name_ltc_mxn), coinImage = R.drawable.ic_bch, coinKey = "bch_btc"),
    bch_mxn(coinName = getStringRes(R.string.book_order_name_bch_mxn), coinImage = R.drawable.ic_bch, coinKey = "bch_mxn"),
    tusd_btc(coinName = getStringRes(R.string.book_order_name_tusd_btc), coinImage = R.drawable.ic_btc, coinKey = "tusd_btc"),
    mana_btc(coinName = getStringRes(R.string.book_order_name_mana_btc), coinImage = R.drawable.ic_btc, coinKey = "mana_btc"),

    mana_mxn(coinName = getStringRes(R.string.book_order_name_mana_mxn), coinImage = R.drawable.ic_cryptocurrency, coinKey = "mana_mxn"),
    bat_btc(coinName = "bat_btc", coinImage = R.drawable.ic_btc, coinKey = "bat_btc"),
    btc_usd(coinName = "btc_usd", coinImage = R.drawable.ic_btc, coinKey = "btc_usd"),
    xrp_usd(coinName = "xrp_usd", coinImage = R.drawable.ic_xrp, coinKey = "xrp_usd"),

    btc_dai(coinName = "btc_dai", coinImage = R.drawable.ic_btc, coinKey = "btc_dai"),
    dai_mxn(coinName = "dai_mxn", coinImage = R.drawable.ic_cryptocurrency, coinKey = "dai_mxn"),
    bat_mxn(coinName = "bat_mxn", coinImage = R.drawable.ic_cryptocurrency, coinKey = "bat_mxn"),
    btc_ars(coinName = "btc_ars", coinImage = R.drawable.ic_btc, coinKey = "btc_ars"),

    eth_usd(coinName = "eth_usd", coinImage = R.drawable.ic_ethereum, coinKey = "eth_usd"),
    dai_ars(coinName = "dai_ars", coinImage = R.drawable.ic_btc, coinKey = "dai_ars"),
    btc_brl(coinName = "btc_brl", coinImage = R.drawable.ic_btc, coinKey = "btc_brl"),
    eth_ars(coinName = "eth_ars", coinImage = R.drawable.ic_ethereum, coinKey = "eth_ars"),

    any_any("crypto", coinImage = R.drawable.ic_ldoge, coinKey = "any_any"),
}

fun genExchangeBookOrder(
    buyerCryptoCoinUI: CryptoCoinUI,
    buyerCryptoCoinKey: String,
    sellerCryptoCoinUI: CryptoCoinUI,
    sellerCryptoCoinKey: String
) : String {
    val buyer = if (buyerCryptoCoinUI == CryptoCoinUI.crypto) buyerCryptoCoinKey.uppercase(
        Locale.ENGLISH
    ) else buyerCryptoCoinUI.coinName

    val seller = if (sellerCryptoCoinUI == CryptoCoinUI.crypto) sellerCryptoCoinKey.uppercase(
        Locale.ENGLISH
    ) else sellerCryptoCoinUI.coinName

    return "$buyer \u2022 $seller"
}

fun getStringRes(id: Int): String {
    return ZeroCoinsApplication.appContext.getString(id)
}

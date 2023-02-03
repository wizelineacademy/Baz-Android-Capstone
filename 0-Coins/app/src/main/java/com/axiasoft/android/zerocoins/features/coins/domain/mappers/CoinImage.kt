package com.axiasoft.android.zerocoins.features.coins.domain.mappers

import android.content.res.Resources
import com.axiasoft.android.zerocoins.R

enum class CoinNameAndImage(val coinName: String, val coinImage: Int, val coinKey: String) {

    btc_mxn(coinName = getStringRes(R.string.book_order_name_btc_mxn), coinImage = R.drawable.ic_btc, coinKey = "btc_mxn"),
    eth_btc(coinName = getStringRes(R.string.book_order_name_eth_btc), coinImage = R.drawable.ic_ethereum, coinKey = "eth_btc"),
    eth_mxn(coinName = "eth_mxn", coinImage = R.drawable.ic_ethereum, coinKey = "eth_mxn"),
    xrp_btc(coinName = "xrp_btc", coinImage = R.drawable.ic_xrp, coinKey = "xrp_btc"),
    xrp_mxn(coinName = "xrp_mxn", coinImage = R.drawable.ic_xrp, coinKey = "xrp_mxn"),
    ltc_btc(coinName = "ltc_btc", coinImage = R.drawable.ic_litecoin, coinKey = "ltc_btc"),
    ltc_mxn(coinName = "ltc_mxn", coinImage = R.drawable.ic_litecoin, coinKey = "ltc_mxn"),
    bch_btc(coinName = "btc_mxn", coinImage = R.drawable.ic_bch, coinKey = "bch_btc"),
    bch_mxn(coinName = "bch_mxn", coinImage = R.drawable.ic_bch, coinKey = "btc_mxn"),
    tusd_btc(coinName = "tusd_btc", coinImage = R.drawable.ic_btc, coinKey = "tusd_btc"),
    mana_btc(coinName = "mana_btc", coinImage = R.drawable.ic_btc, coinKey = "mana_btc"),

    mana_mxn(coinName = "mana_mxn", coinImage = R.drawable.ic_cryptocurrency, coinKey = "mana_mxn"),
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

    any_any("crypto", coinImage = R.drawable.ic_ldoge, coinKey = "any_any")
}

fun getStringRes(id: Int): String{
    //val resName = Resources.getSystem().getIdentifier()
    return "Moneda"//resName//Resources.getSystem().getString(id)
}
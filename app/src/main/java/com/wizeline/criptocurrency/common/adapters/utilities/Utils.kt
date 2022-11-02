package com.wizeline.criptocurrency.common.adapters.utilities

import com.wizeline.criptocurrency.R

fun getCoinImage(coin: String): Int {
    val image = when(coin) {
        CoinType.BITCOIN.value -> R.drawable.bitcoin
        CoinType.ETHEREUM.value -> R.drawable.etherium
        CoinType.XRP.value -> R.drawable.xrp
        CoinType.LITECOIN.value -> R.drawable.litecoin
        CoinType.BITCOIN_CASH.value -> R.drawable.bitcoincash
        CoinType.TRUEUSD.value -> R.drawable.tusd
        CoinType.DECETRALAND.value -> R.drawable.mana
        CoinType.BASIC_ATENTION_TOKEN.value -> R.drawable.bat
        CoinType.DAI.value -> R.drawable.dai
        CoinType.USD_COIN.value -> R.drawable.usd
        else -> android.R.drawable.sym_def_app_icon
    }
    return image
}
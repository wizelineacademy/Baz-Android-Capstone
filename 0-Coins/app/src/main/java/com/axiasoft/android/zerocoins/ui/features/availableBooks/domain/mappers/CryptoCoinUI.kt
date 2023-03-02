package com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.mappers

import com.axiasoft.android.zerocoins.R

enum class CryptoCoinUI(val coinName: String, val coinImage: Int, val coinKey: String) {

    btc(coinName = getStringRes(R.string.btc), coinImage = R.drawable.ic_btc, coinKey = "btc"),
    eth(coinName = getStringRes(R.string.eth), coinImage = R.drawable.ic_ethereum, coinKey = "eth"),
    mxn(coinName = getStringRes(R.string.mxn), coinImage = R.drawable.ic_mexican_peso, coinKey = "mxn"),
    xrp(coinName = getStringRes(R.string.xrp), coinImage = R.drawable.ic_xrp, coinKey = "xrp"),
    ltc_(coinName = getStringRes(R.string.ltc), coinImage = R.drawable.ic_litecoin, coinKey = "ltc"),
    bch(coinName = getStringRes(R.string.bch), coinImage = R.drawable.ic_bch, coinKey = "bch"),
    tusd(coinName = getStringRes(R.string.tusd), coinImage = R.drawable.ic_tusd, coinKey = "tusd"),
    mana(coinName = getStringRes(R.string.mana), coinImage = R.drawable.ic_mana, coinKey = "mana"),

    bat(coinName = getStringRes(R.string.bat), coinImage = R.drawable.ic_bat, coinKey = "bat"),
    usd(coinName = getStringRes(R.string.usd), coinImage = R.drawable.ic_usd, coinKey = "usd"),

    dai(coinName = getStringRes(R.string.dai), coinImage = R.drawable.ic_dai, coinKey = "dai"),

    ars(coinName = getStringRes(R.string.ars), coinImage = R.drawable.ic_ars, coinKey = "ars"),

    brl(coinName = getStringRes(R.string.brl), coinImage = R.drawable.ic_brl, coinKey = "brl"),
    comp(coinName = getStringRes(R.string.comp), coinImage = R.drawable.ic_comp, coinKey = "comp"),
    gala(coinName = getStringRes(R.string.gala), coinImage = R.drawable.ic_gala, coinKey = "gala"),
    doge(coinName = getStringRes(R.string.doge), coinImage = R.drawable.ic_ldoge, coinKey = "doge"),
    eur(coinName = getStringRes(R.string.eur), coinImage = R.drawable.ic_eur, coinKey = "eur"),
    sol(coinName = getStringRes(R.string.sol), coinImage = R.drawable.ic_solana, coinKey = "sol"),
    shib(coinName = getStringRes(R.string.shib), coinImage = R.drawable.ic_shib, coinKey = "shib"),
    omg(coinName = getStringRes(R.string.omg), coinImage = R.drawable.ic_omg, coinKey = "omg"),

    crypto("crypto", coinImage = R.drawable.ic_cryptocurrency, coinKey = "crypto"),
}

package com.example.readbitso.support.coinsdefinition

import com.example.readbitso.R

class Btc : Coins() {
    override fun getCoin(): String = "Bitcoin"
    override fun getIcon(): Int = R.drawable.cripto_bitcoin
    override fun getCoinShorter() = "BTC"
}

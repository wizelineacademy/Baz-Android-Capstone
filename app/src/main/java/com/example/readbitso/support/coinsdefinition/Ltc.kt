package com.example.readbitso.support.coinsdefinition

import com.example.readbitso.R

class Ltc : Coins() {
    override fun getCoin(): String = "Little Coin"
    override fun getIcon(): Int = R.drawable.cripto_ltc
    override fun getCoinShorter() = "LTC"
}

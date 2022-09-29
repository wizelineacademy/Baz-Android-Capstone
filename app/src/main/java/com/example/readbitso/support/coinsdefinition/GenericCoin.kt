package com.example.readbitso.support.coinsdefinition

import com.example.readbitso.R

class GenericCoin : Coins() {
    override fun getCoin(): String = "Generic"
    override fun getIcon(): Int = R.drawable.cripto_default
    override fun getCoinShorter() = "Def"
}

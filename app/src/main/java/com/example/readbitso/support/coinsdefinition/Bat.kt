package com.example.readbitso.support.coinsdefinition

import com.example.readbitso.R

class Bat : Coins() {
    override fun getCoin(): String = "Basic Attention Token"
    override fun getIcon(): Int = R.drawable.cripto_bat
    override fun getCoinShorter() = "BAT"
}

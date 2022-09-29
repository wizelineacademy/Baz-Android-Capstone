package com.example.readbitso.support.coinsdefinition

import com.example.readbitso.R

class Xrp : Coins() {
    override fun getCoin(): String = "XRP/Ripple"
    override fun getIcon(): Int = R.drawable.cripto_xrp
    override fun getCoinShorter() = "XRP"
}

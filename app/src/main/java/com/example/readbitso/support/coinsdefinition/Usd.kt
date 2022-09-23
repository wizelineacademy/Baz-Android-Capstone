package com.example.readbitso.support.coinsdefinition

import com.example.readbitso.R


class Usd: Coins(){
    override fun getCoin(): String ="USD"
    override fun getIcon(): Int = R.drawable.cripto_usd
    override fun getCoinShorter()="USD"
}
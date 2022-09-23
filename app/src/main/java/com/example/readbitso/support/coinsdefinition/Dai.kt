package com.example.readbitso.support.coinsdefinition

import com.example.readbitso.R

class Dai: Coins(){
    override fun getCoin(): String ="DAI"
    override fun getIcon(): Int = R.drawable.cripto_dai
    override fun getCoinShorter()="DAI"
}
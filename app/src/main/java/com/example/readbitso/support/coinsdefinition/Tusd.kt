package com.example.readbitso.support.coinsdefinition

import com.example.readbitso.R

class Tusd: Coins(){
    override fun getCoin(): String ="True USD"
    override fun getIcon(): Int = R.drawable.cripto_tusd
    override fun getCoinShorter()="TUSD"
}
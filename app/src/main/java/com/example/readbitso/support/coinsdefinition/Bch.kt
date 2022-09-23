package com.example.readbitso.support.coinsdefinition

import com.example.readbitso.R


class Bch: Coins(){
    override fun getCoin(): String ="Bitcoin Cash"
    override fun getIcon(): Int = R.drawable.cripto_bhc
    override fun getCoinShorter()="BCH"
}
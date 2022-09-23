package com.example.readbitso.support.coinsdefinition

import com.example.readbitso.R


class Mana: Coins(){
    override fun getCoin(): String ="Mana/Decentraland "
    override fun getIcon(): Int = R.drawable.cripto_mana
    override fun getCoinShorter()="MANA"
}
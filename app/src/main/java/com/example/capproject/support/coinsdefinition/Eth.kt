package com.example.capproject.support.coinsdefinition

import com.example.capproject.R

class Eth:Coins(){
    override fun getCoin(): String ="Ethereum"
    override fun getIcon(): Int = R.drawable.cripto_ethereum
    override fun getCoinShorter()="ETH"
}
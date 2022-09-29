package com.example.readbitso.support.coinsdefinition
import com.example.readbitso.R

class Eth : Coins() {
    override fun getCoin(): String = "Ethereum"
    override fun getIcon(): Int = R.drawable.cripto_ethereum
    override fun getCoinShorter() = "ETH"
}

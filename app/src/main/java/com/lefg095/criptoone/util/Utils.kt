package com.lefg095.criptoone.util

import com.lefg095.criptoone.R

fun getIdResource(coin: String): Int{
    return when(coin){
        "btc" -> R.drawable.btc
        "eth" -> R.drawable.eth
        "xrp" -> R.drawable.xrp
        "ltc" -> R.drawable.ltc
        "bch" -> R.drawable.btch
        "bat" -> R.drawable.bat
        "dai" -> R.drawable.dai
        "mana" -> R.drawable.mana
        "sol" -> R.drawable.solana
        "tusd" -> R.drawable.tusd
        "doge" -> R.drawable.doge
        "dot" -> R.drawable.dot
        "usd" -> R.drawable.usd
        "comp" -> R.drawable.comp
        "link" -> R.drawable.link
        "aave" -> R.drawable.aave
        "dydx" -> R.drawable.dykx
        "yfi" -> R.drawable.yfi
        "uni" -> R.drawable.uni
        "chz" -> R.drawable.chz
        "xlm" -> R.drawable.xlm
        "ada" -> R.drawable.ada
        "paxg" -> R.drawable.paxq
        "shib" -> R.drawable.shib
        "matic" -> R.drawable.matic
        "axs" -> R.drawable.axs
        "sand" -> R.drawable.sand
        "snx" -> R.drawable.snx
        "mkr" -> R.drawable.mkr
        "gala" -> R.drawable.gala
        "ftm" -> R.drawable.ftm
        "lrc" -> R.drawable.lcr
        "omg" -> R.drawable.omg
        "algo" -> R.drawable.algo
        "crv" -> R.drawable.crv
        "grt" -> R.drawable.grt
        "ape" -> R.drawable.ape
        "sushi" -> R.drawable.sushi
        "qnt" -> R.drawable.qnt
        "bal" -> R.drawable.bal
        "trx" -> R.drawable.trx
        "ldo" -> R.drawable.ldo
        "usdt" -> R.drawable.usdt
        "enj" -> R.drawable.enj
        else -> R.drawable.gral_coin
    }
}
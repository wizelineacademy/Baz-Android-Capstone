package com.example.capproject.support

fun monedas (name:String): String{
    val icon = when(name){
        "btc_mxn"-> "Bitcoin"
        "eth_mxn"-> "Etherum"
        "xrp_mxn"-> "XRP/Rpple"
        "ltc_mxn"-> "LittleCoin"
        "bch_mxn"-> "Bitcoin Cash"
        "tusd_mxn"-> "True USD"
        "mana_mxn"-> "MANA/Decentraland"
        "dai_mxn"-> "DAI"
        "usd_mxn"-> "USD"
        "bat_mxn"-> "Basic Attention Token"
        else -> name
    }
    return icon
}

fun operation(name:String):String{
    val names=when(name)
    {
        "sell"-> "Venta"
        "buy"-> "Compra"
        else -> {""}
    }
    return names
}

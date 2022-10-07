package com.example.readbitso.support

import android.util.Log
import com.example.readbitso.R

fun tokens(name: String): String {
    val icon = when (name) {
        "btc_mxn" -> CoinsDefinition.Bitcoin.token
        "eth_mxn" -> CoinsDefinition.Ethereum.token
        "xrp_mxn" -> CoinsDefinition.Xrp.token
        "ltc_mxn" -> CoinsDefinition.Ltc.token
        "bch_mxn" -> CoinsDefinition.Bhc.token
        "tusd_mxn" -> CoinsDefinition.Tusd.token
        "mana_mxn" -> CoinsDefinition.Mana.token
        "dai_mxn" -> CoinsDefinition.Dai.token
        "usd_mxn" -> CoinsDefinition.Usd.token
        "bat_mxn" -> CoinsDefinition.Bat.token
        else -> name
    }
    return icon
}

fun operationKind(name: String): String {
    val names = when (name) {
        "sell" -> "Venta"
        "buy" -> "Compra"
        else -> { name }
    }
    return names
}

fun icon(icons: String): Int {
    val icon = when (icons) {
        "btc_mxn" -> CoinsDefinition.Bitcoin.icon
        "eth_mxn" -> CoinsDefinition.Ethereum.icon
        "xrp_mxn" -> CoinsDefinition.Xrp.icon
        "ltc_mxn" -> CoinsDefinition.Ltc.icon
        "bch_mxn" -> CoinsDefinition.Bhc.icon
        "tusd_mxn" -> CoinsDefinition.Tusd.icon
        "mana_mxn" -> CoinsDefinition.Mana.icon
        "dai_mxn" -> CoinsDefinition.Dai.icon
        "usd_mxn" -> CoinsDefinition.Usd.icon
        "bat_mxn" -> CoinsDefinition.Bat.icon
        else -> { R.drawable.cripto_default }
    }
    return icon
}

fun shortToken(name: String): String {
    val shortname = when (name) {
        "btc_mxn" -> CoinsDefinition.Bitcoin.shortName
        "eth_mxn" -> CoinsDefinition.Ethereum.shortName
        "xrp_mxn" -> CoinsDefinition.Xrp.shortName
        "ltc_mxn" -> CoinsDefinition.Ltc.shortName
        "bch_mxn" -> CoinsDefinition.Bhc.shortName
        "tusd_mxn" -> CoinsDefinition.Tusd.shortName
        "mana_mxn" -> CoinsDefinition.Mana.shortName
        "dai_mxn" -> CoinsDefinition.Dai.shortName
        "usd_mxn" -> CoinsDefinition.Usd.shortName
        "bat_mxn" -> CoinsDefinition.Bat.shortName
        else -> name
    }
    return shortname
}

fun loggerD(message: String) {
    val default = "peticion"
    Log.d(default, message)
}

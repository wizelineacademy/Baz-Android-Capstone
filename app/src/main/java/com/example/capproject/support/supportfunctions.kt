package com.example.capproject.support

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.capproject.R

fun monedas (name:String): String{
    val icon = when(name){
        "btc_mxn"-> "Bitcoin"
        "eth_mxn"-> "Etherum"
        "xrp_mxn"-> "XRP/Ripple"
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
fun iconos (icono:String): Int {
    val icon: Int = when(icono){
        "btc_mxn"-> R.drawable.cripto_bitcoin
        "eth_mxn"-> R.drawable.cripto_ethereum
        "xrp_mxn"-> R.drawable.cripto_xrp
        "ltc_mxn"-> R.drawable.cripto_ltc
        "bch_mxn"-> R.drawable.cripto_bhc
        "tusd_mxn"-> R.drawable.cripto_tusd
        "mana_mxn"-> R.drawable.cripto_mana
        "dai_mxn"-> R.drawable.cripto_dai
        "usd_mxn"-> R.drawable.cripto_usd
        "bat_mxn"-> R.drawable.cripto_bat
        else -> R.drawable.cripto_default
    }
    return icon
}

fun moneda (icono:String): String{
    val icon = when(icono){
        "btc_mxn"-> "BTC"
        "eth_mxn"-> "ETH"
        "xrp_mxn"-> "XRP"
        "ltc_mxn"-> "LTC"
        "bch_mxn"-> "BCH"
        "tusd_mxn"-> "TUSD"
        "mana_mxn"-> "MANA"
        "dai_mxn"-> "DAI"
        "usd_mxn"-> "USD"
        "bat_mxn"-> "BAT"
        else -> icono
    }
    return icon
}

fun loggerD(default:String="peticion ",message:String){
    Log.d(default,message)
}


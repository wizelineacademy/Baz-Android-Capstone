package com.example.capproject.support

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.capproject.R
import com.example.capproject.support.coinsdefinition.*

fun tokens (name:String): String{
    val icon = when(name){
        "btc_mxn"-> Btc().getCoin()
        "eth_mxn"-> Eth().getCoin()
        "xrp_mxn"-> Xrp().getCoin()
        "ltc_mxn"-> Ltc().getCoin()
        "bch_mxn"-> Bch().getCoin()
        "tusd_mxn"-> Tusd().getCoin()
        "mana_mxn"-> Mana().getCoin()
        "dai_mxn"-> Dai().getCoin()
        "usd_mxn"-> Usd().getCoin()
        "bat_mxn"-> Bat().getCoin()
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
fun icon (icons:String): Int {
    val icon: Int = when(icons){
        "btc_mxn"-> Btc().getIcon()
        "eth_mxn"-> Eth().getIcon()
        "xrp_mxn"-> Xrp().getIcon()
        "ltc_mxn"-> Ltc().getIcon()
        "bch_mxn"-> Bch().getIcon()
        "tusd_mxn"-> Tusd().getIcon()
        "mana_mxn"-> Mana().getIcon()
        "dai_mxn"-> Dai().getIcon()
        "usd_mxn"-> Usd().getIcon()
        "bat_mxn"-> Bat().getIcon()
        else -> {return -1}
    }
    return icon
}

fun shortToken (name:String): String{
    val shortname = when(name){
        "btc_mxn"-> Btc().getCoinShorter()
        "eth_mxn"-> Eth().getCoinShorter()
        "xrp_mxn"-> Xrp().getCoinShorter()
        "ltc_mxn"-> Ltc().getCoinShorter()
        "bch_mxn"-> Bch().getCoinShorter()
        "tusd_mxn"-> Tusd().getCoinShorter()
        "mana_mxn"-> Mana().getCoinShorter()
        "dai_mxn"-> Dai().getCoinShorter()
        "usd_mxn"-> Usd().getCoinShorter()
        "bat_mxn"-> Bat().getCoinShorter()
        else -> name
    }
    return shortname
}

fun loggerD(default:String="peticion ",message:String){
    Log.d(default,message)
}


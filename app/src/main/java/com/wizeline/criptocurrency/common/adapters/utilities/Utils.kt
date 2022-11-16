package com.wizeline.criptocurrency.common.adapters.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.wizeline.criptocurrency.R
import java.text.NumberFormat

fun Double.formatAsCurrency(): String = NumberFormat.getCurrencyInstance().format(this)

fun getCoinImage(coin: String): Int {
    val image = when (coin) {
        CoinType.BITCOIN.value -> R.drawable.bitcoin
        CoinType.ETHEREUM.value -> R.drawable.etherium
        CoinType.XRP.value -> R.drawable.xrp
        CoinType.LITECOIN.value -> R.drawable.litecoin
        CoinType.BITCOIN_CASH.value -> R.drawable.bitcoincash
        CoinType.TRUEUSD.value -> R.drawable.tusd
        CoinType.DECETRALAND.value -> R.drawable.mana
        CoinType.BASIC_ATTENTION_TOKEN.value -> R.drawable.bat
        CoinType.DAI.value -> R.drawable.dai
        CoinType.USD_COIN.value -> R.drawable.usd
        else -> android.R.drawable.sym_def_app_icon
    }
    return image
}

fun getCoinName(coin: String): String {
    val name = when (coin) {
        CoinType.BITCOIN.value -> "Bitcoin"
        CoinType.ETHEREUM.value -> "Etherium"
        CoinType.XRP.value -> "XRP"
        CoinType.LITECOIN.value -> "Litecoin"
        CoinType.BITCOIN_CASH.value -> "Bitcoin Cash"
        CoinType.TRUEUSD.value -> "True USD"
        CoinType.DECETRALAND.value -> "Mana"
        CoinType.BASIC_ATTENTION_TOKEN.value -> "BAT"
        CoinType.DAI.value -> "Dai"
        CoinType.USD_COIN.value -> "USD Coin"
        else -> "Coin Name"
    }
    return name
}

fun Fragment.toast(text: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), text, length).show()
}

fun isInternetAvailable(context: Context): Boolean {
    var result = false
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        connectivityManager.run {
            connectivityManager.activeNetworkInfo?.run {
                result = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
    }

    return result
}

package com.example.cryptocurrencyapp.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import com.example.cryptocurrencyapp.R
import java.text.Normalizer

object Utils {

    var dialog: AlertDialog? = null
    fun cleanString(text: String): String {
        var query = text
        query = Normalizer.normalize(text, Normalizer.Form.NFD)
        query = text.replace("[\\p{InCombiningDiacriticalMarks}]".toRegex(), "")
        return query
    }


   fun errorDialog(activity: Activity)
   {
       val builder = AlertDialog.Builder(activity)
       builder.setTitle("ERROR").setMessage("Error al cargar datos, intentarlo más tarde")
       dialog = builder.create()
   }
    fun showDialog(){
        dialog?.show()
    }






    enum class CoinType(val value: String, val coin: String, val logo: Int) {
        BITCOIN("btc_mxn", "Bitcoin",R.drawable.ic_bitcoin),
        ETHEREUM("eth_mxn", "Ethereum",R.drawable.ic_ethereum),
        XRP("xrp_mxn", "XRP",R.drawable.ic_xrp),
        LITECOIN("ltc_mxn", "Litecoin",R.drawable.ic_litecoin),
        BITCOIN_CASH("bch_mxn", "Bitcoin Cash",R.drawable.ic_litecoin),
        TRUEUSD("tusd_mxn", "True USD",R.drawable.ic_litecoin),
        DECETRALAND("mana_mxn", "Decentraland",R.drawable.ic_litecoin),
        BASIC_ATENTION_TOKEN("bat_mxn", "Basic Attention Token",R.drawable.ic_litecoin),
        DAI("dai_mxn", "Dai",R.drawable.ic_litecoin),
        USD_COIN("usd_mxn", "USD coin",R.drawable.ic_litecoin)
    }


}
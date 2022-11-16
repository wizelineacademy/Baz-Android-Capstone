package com.example.criptobitsoproyectwz.data.model

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.criptobitsoproyectwz.R


class CriptoImage {

    fun match(cripto: String ): Int {

           return when(cripto){
                "btc_mxn"->{
                   R.drawable.btc
                }
               "eth_mxn"->{
                   R.drawable.eth
               }
               "xrp_mxn"->{
                   R.drawable.xrp
               }
               "ltc_mxn"->{
                   R.drawable.ltc
               }
               "bch_mxn"->{
                   R.drawable.bch
               }
               "tusd_mxn"->{
                   R.drawable.tusd
               }
               "mana_mxn"->{
                   R.drawable.mana
               }
               "bat_mxn"->{
                   R.drawable.bat
               }
               "dai_mxn"->{
                   R.drawable.dai
               }
               "usd_mxn"->{
                   R.drawable.usd
               }
                else ->{
                   R.drawable.bitso
                }
            }

    }

}


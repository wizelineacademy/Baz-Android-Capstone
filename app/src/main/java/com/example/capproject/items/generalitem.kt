package com.example.capproject.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.capproject.R
import com.example.capproject.models.Book.Payload


//item informativo divisas
@Composable
fun Generalitem2(lista: Payload) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(2.dp)) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
            border = BorderStroke(width = 2.dp, Color.Black)) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
              , horizontalArrangement = Arrangement.Start)
            {
                Box(modifier = Modifier
                    .width(63.dp)
                    .height(72.dp)
                )
                {
                    Column(modifier = Modifier.padding(top=8.dp, start = 8.dp), verticalArrangement = Arrangement.Top
                        , horizontalAlignment = Alignment.CenterHorizontally)

                    {
                        Image(modifier = Modifier.padding(4.dp),painter = painterResource(id = iconos(lista.book)?:-1), contentDescription = null)
                        Text(text = moneda(lista.book), modifier = Modifier.padding(start = 8.dp))
                    }
                }
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start, modifier = Modifier.padding(start = 40.dp)) {
                    Text(text = "Maximo Historico \$${lista.maximum_price} ",
                                               modifier = Modifier.padding(start = 16.dp))
                    Text(text = "Minimo  Historico \$${lista.minimum_price} ",
                        modifier = Modifier.padding(start = 16.dp, top = 2.dp))
                }
            }
        }
    }
}

private fun iconos (icono:String): Int {
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

private fun moneda (icono:String): String{
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

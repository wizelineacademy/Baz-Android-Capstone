package com.example.capproject.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.capproject.R
import com.example.capproject.models.book.Payload

@Composable
fun generalitem(name: String, maximumValue: String, minimumPrice: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(2.dp)
        .background(Color.LightGray)){
        Card(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
        border = BorderStroke(width = 2.dp, Color.Black)) {
            Row(modifier = Modifier.fillMaxWidth()) {

                Image(painter = painterResource(id = if (name == "btc_mxn")
                    R.drawable.cripto_bitcoin
                else R.drawable.cripto_default), contentDescription = null)
                Text(text = name, modifier = Modifier.padding(start = 8.dp))

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = "Precio maximo :$maximumValue ", modifier = Modifier.padding(start = 8.dp))
                    Text(text = "Precio minimo :$minimumPrice ", modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
        
    }
}

@Composable
fun generalitem2(lista: Payload) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(2.dp)
        .background(Color.LightGray)) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
            border = BorderStroke(width = 2.dp, Color.Black)) {
            Row(modifier = Modifier.fillMaxWidth()) {

                Image(painter = painterResource(id = if ( lista.book== "btc_mxn")
                        R . drawable . cripto_bitcoin
                        else R . drawable . cripto_default), contentDescription = null)
                Text(text = lista.book, modifier = Modifier.padding(start = 8.dp))

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = "Precio maximo :${lista.maximum_price} ",
                        modifier = Modifier.padding(start = 8.dp))
                    Text(text = "Precio minimo :${lista.minimum_price} ",
                        modifier = Modifier.padding(start = 8.dp))
                }
            }
        }

    }
}



package com.example.capproject.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.capproject.models.Tickers.Payload


@Composable
fun Iteminfo ( lista: Payload){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(2.dp)) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
            border = BorderStroke(width = 2.dp, Color.Black)
        ) {
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
                        Image(modifier = Modifier.padding(4.dp),painter = painterResource(id = iconos(lista.book)), contentDescription = null)
                        Text(text = moneda(lista.book), modifier = Modifier.padding(start = 8.dp))
                    }
                }
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start, modifier = Modifier.padding(start = 40.dp)) {
                    Text(text = "Ask \$${lista.ask} ",
                        modifier = Modifier.padding(start = 16.dp))
                    Text(text = "Bid \$${lista.bid} ",
                        modifier = Modifier.padding(start = 16.dp, top = 2.dp))
                }
            }
        }
    }
}



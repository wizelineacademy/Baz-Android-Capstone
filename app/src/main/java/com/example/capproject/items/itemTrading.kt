package com.example.capproject.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.capproject.models.trading.PayloadTrades
import com.example.capproject.support.operation

@Composable
fun ItemTrading (lista: PayloadTrades) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(2.dp)) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
            border = BorderStroke(width = 2.dp, Color.Black)) {

            Column {
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween)
                {
                    Text(text =  lista.amount.take(10),
                        modifier = Modifier.padding(start = 8.dp))
                    Text(text = " ${operation(lista.maker_side)} ",
                        modifier = Modifier.padding(start = 8.dp),
                        color = if (lista.maker_side == "sell")
                            Color.Red
                        else
                            Color.Green
                    )
                    Text(text = " \$${lista.price} ",
                        modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }
}
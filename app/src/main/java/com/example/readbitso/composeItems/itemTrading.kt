package com.example.readbitso.composeItems

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
import com.example.readbitso.models.trading.PayloadTrades

@Composable
fun ItemTrading (list: PayloadTrades) {
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
                    Text(text =  list.amount.take(10),
                        modifier = Modifier.padding(start = 8.dp))
                    Text(text = list.maker_side,
                        modifier = Modifier.padding(start = 8.dp),
                        color = if (list.maker_side == "Venta")
                            Color.Red
                        else
                            Color.Green
                    )
                    Text(text = " \$${list.price.take(10)} ",
                        modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }
}
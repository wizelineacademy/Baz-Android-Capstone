package com.example.readbitso.composeItems

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
import com.example.readbitso.models.bitsoModels.bitsoBooks.bitsotickers.PayloadTickers
import com.example.readbitso.support.icon
import com.example.readbitso.support.shortToken


@Composable
fun MoneyDetails ( list: PayloadTickers){
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
                        Image(modifier = Modifier.padding(4.dp),painter = painterResource(id = icon(list.book.toString())), contentDescription = null)
                        Text(text = shortToken(list.book.toString()), modifier = Modifier.padding(start = 8.dp))
                    }
                }
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(start = 40.dp)) {
                    Text(text = "Ask \$${list.ask} ",
                        modifier = Modifier.padding(start = 16.dp))
                    Text(text = "Bid \$${list.bid} ",
                        modifier = Modifier.padding(start = 16.dp, top = 2.dp))
                }
            }
        }
    }
}



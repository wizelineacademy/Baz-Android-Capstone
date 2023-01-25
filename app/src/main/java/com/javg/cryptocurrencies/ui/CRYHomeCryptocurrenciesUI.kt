package com.javg.cryptocurrencies.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javg.cryptocurrencies.R

@Composable
fun CRYHomeCryptocurrenciesScreen(){
    LazyColumn(modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)){
        items(10){
            CRYCardInformation()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CRYCardInformation(){
    Card(modifier = Modifier
        .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = 3.dp) {

        Row(Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_bitcoin),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp))
            Spacer(Modifier.size(12.dp))
            Column{
                Text(text = "BTC MXN",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.size(8.dp))
                Text(text = stringResource(id = R.string.cry_maximum_price))
                Spacer(Modifier.size(8.dp))
                Text(text = stringResource(id = R.string.cry_maximum_amount))
                Spacer(Modifier.size(8.dp))
                Text(text = stringResource(id = R.string.cry_minimum_amount))
            }
        }
    }
}
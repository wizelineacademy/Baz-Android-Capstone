package com.example.criptobitsoproyectwz.ui.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.criptobitsoproyectwz.data.model.CriptoImage
import com.example.criptobitsoproyectwz.data.model.OrderBook.Asks
import com.example.criptobitsoproyectwz.data.model.OrderBook.Bids
import com.example.criptobitsoproyectwz.data.model.Ticket.PayloadCripto
import com.example.criptobitsoproyectwz.ui.ViewModel.ViewModelCripto
import java.text.NumberFormat
import java.util.*

@Composable
fun DetallesScreen(navController: NavHostController, cripto: String) {
   /* val infoCriptoVM = viewModel(modelClass = ViewModelCripto::class.java)
    infoCriptoVM.getCriptosInfo(cripto)
    val info by infoCriptoVM.dataCriptoInfo.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (info.isNullOrEmpty()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp, 50.dp)
            )
        } else {
            CardDetalle(cripto, info)
            OtraFORMA(cripto = cripto)
        }
    }
}

@Composable
fun OtraFORMA(cripto: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize()
    ) {
        Ask(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(5.dp))
        Bids(cripto = cripto, modifier = Modifier.weight(1f))
    }
}

@Composable
fun Ask(modifier: Modifier = Modifier) {
    val infoAskBids = viewModel(modelClass = ViewModelCripto::class.java)
    val asks by infoAskBids.dataAsks.collectAsState()

    LazyColumn(modifier = modifier) {
        if (asks.isNullOrEmpty()) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp, 50.dp)
                )
            }
        }
        items(asks) { list ->
            cardAsks(list)
        }
    }

}

@Composable
fun Bids(cripto: String, modifier: Modifier = Modifier) {
    val infoAskBids = viewModel(modelClass = ViewModelCripto::class.java)
    infoAskBids.getAskBids(cripto)
    infoAskBids.getAllAskBids(cripto = cripto)
    val bids by infoAskBids.dataBids.collectAsState()

    LazyColumn(modifier = modifier) {
        if (bids.isNullOrEmpty()) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp, 50.dp)
                )
            }
        }

        items(bids) { list ->
            cardBids(list)
        }
    }
}


@Composable
private fun cardBids(list: Bids) {
    val currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US)
    Card(
        modifier = Modifier
            .padding(start = 2.dp, bottom = 10.dp, end = 6.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = 5.dp,
       border = BorderStroke(1.dp, Color.Black)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.padding(2.dp)) {
                Text(
                    text = "Price:${currencyFormatter.format(list.price)}",
                    color = Color.Black,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = "Amount: ${currencyFormatter.format(list.amount)}",
                    color = Color.Black,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
            }
        }
    }
}

@Composable
private fun cardAsks(list: Asks) {
    val currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US)
    Card(
        modifier = Modifier
            .padding(start = 2.dp, bottom = 10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface,
        border = BorderStroke(1.dp, Color.Black)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = "Price:${currencyFormatter.format(list.price)}",
                    color = Color.Black,
                    fontSize = 10.sp,
                )
                Text(
                    text = "Amount:${currencyFormatter.format(list.amount)}",
                    color = Color.Black,
                    fontSize = 10.sp,

                )
            }
        }
    }
}

@Composable
fun CardDetalle(cripto: String, info: List<PayloadCripto>) {
    val imageCrip = CriptoImage()
    val imagePainter = imageCrip.match(cripto = cripto)
    val image = painterResource(id = imagePainter)

    val currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US)
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .padding(8.dp),
            contentScale = ContentScale.Fit,
        )
        Column(Modifier.padding(8.dp)) {
            Text(
                text = cripto,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
                color = MaterialTheme.colors.onSurface,
            )
            Text(
                text = "High: ${currencyFormatter.format(info[0].high)}",
                style = MaterialTheme.typography.overline,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Low: ${currencyFormatter.format(info[0].low)}",
                style = MaterialTheme.typography.overline,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Last: ${currencyFormatter.format(info[0].last)}",
                style = MaterialTheme.typography.overline,
                modifier = Modifier.padding(bottom = 4.dp)
            )

        }
    }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(1f)
    ) {
        Text(
            text = "Ask",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Text(
            text = "Bids",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }*/

}

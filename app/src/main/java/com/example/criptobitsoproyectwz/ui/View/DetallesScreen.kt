package com.example.criptobitsoproyectwz.ui.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.criptobitsoproyectwz.data.model.CriptoImage
import com.example.criptobitsoproyectwz.data.model.OrderBook.Asks
import com.example.criptobitsoproyectwz.data.model.Ticket.PayloadCripto
import com.example.criptobitsoproyectwz.ui.ViewModel.ViewModelCripto

@Composable
fun DetallesScreen(navController: NavHostController, cripto: String) {
    val infoCriptoVM = viewModel(modelClass = ViewModelCripto::class.java)
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
            BidsAsk(cripto)
        }
    }
}

@Composable
fun BidsAsk(cripto: String) {
    val infoAskBids = viewModel(modelClass = ViewModelCripto::class.java)
    infoAskBids.getAskBids(cripto)
    val asks by infoAskBids.dataAsks.collectAsState()
    val bids by infoAskBids.dataBids.collectAsState()

    LazyColumn(
        modifier = Modifier
            .wrapContentWidth(
                align = Alignment.CenterHorizontally
            )
    ){
        items(asks ){ list ->
            CardInfo(list)
        }
    }


}

@Composable
fun CardInfo(list: Asks) {
    Card(
        modifier = Modifier
            .padding(start = 5.dp )
            .fillMaxWidth()
            .wrapContentHeight()
        ,
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = "Bids:  ${list.price}",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Asks ${list.amount}",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(bottom = 4.dp)
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

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter =  image,
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
                    text = "High: ${info[0].high}",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Low: ${info[0].low}",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}

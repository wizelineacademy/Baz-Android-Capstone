package com.example.capproject.composeviews

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.capproject.viewmodels.BitsoViewModel
import com.example.capproject.items.ItemTrading
import com.example.capproject.items.Iteminfo
import com.example.capproject.models.trading.PayloadTrades
import com.example.capproject.support.monedas

@Composable
fun Detailview(viewModel: BitsoViewModel, navController: NavHostController) {
    with(viewModel) {
        saveState("false")
        val newlist = openedPayloadsCoin
        if (!update)
            Loading()
        else
            Column {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate("start") }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "null")
                        }
                    },
                    title = {
                        Text(monedas(getCoin().toString())
                        )
                    }
                )
                val listtrades = trades
                if (listtrades.isEmpty()) {
                    Loading()
                } else
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.2F),
                        rememberLazyListState(),
                        contentPadding = PaddingValues(16.dp)
                    ) {

                        itemsIndexed(newlist) { _, list ->
                            Iteminfo(list)
                        }
                    }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween)
                {
                    Text(text = "Monto ", modifier = Modifier.padding(start = 8.dp))
                    Text(text = "Tipo de Operación", modifier = Modifier.padding(start = 8.dp))
                    Text(text = "Total Operación")
                }

                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.87F)
                    .padding(16.dp)) {


                    if (listtrades.isNotEmpty()) {
                        val newlistnueva = mutableListOf<PayloadTrades>()
                        listtrades.forEach {
                            newlistnueva.add(it)
                        }

                        itemsIndexed(newlistnueva.take(25)) { _, data ->
                            ItemTrading(list = data)
                        }
                    }
                }

                Text(text = " * Precios expresados en Moneda Nacional", color = Color.LightGray)
                Text(text = "   solo son de carácter informativo", color = Color.LightGray)
                //
            }
    }
}
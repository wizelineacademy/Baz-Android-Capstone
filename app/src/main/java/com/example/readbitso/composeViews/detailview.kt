package com.example.readbitso.composeViews

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.readbitso.BitsoViewmodel
import com.example.readbitso.R
import com.example.readbitso.composeItems.ItemTrading
import com.example.readbitso.composeItems.MoneyDetails

@Composable
fun Detailview(viewModel: BitsoViewmodel, navController: NavHostController) {
    with(viewModel) {
        viewModel.selectPage("second")



        if (!update) {
            if (!isloading)
                when (errormessage) {
                    "" -> LoadingV(stringResource(R.string.consulting))
                    else -> LoadingV(stringResource(R.string.lastconsume))
                }
        } else
            Column {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate("start")
                        })
                        {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "null")
                        }
                    },
                    title = {
                        Text(viewModel.lastconsume)
                    }
                )
                if (trades.isEmpty()) {
                    when (errormessage) {
                        " " -> LoadingV(stringResource(R.string.consulting))
                        else -> LoadingV(stringResource(R.string.lastconsume))
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.2F),
                        rememberLazyListState(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        itemsIndexed(openedPayloadsCoin) { _, list ->
                            MoneyDetails(list)
                        }
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
                    Text(text = "Precio C/V")
                }

                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.85F)
                    .padding(16.dp))
                {
                    if (trades.isNotEmpty())
                        itemsIndexed(trades.take(15)) { _, data ->
                            ItemTrading(list = data)
                        }
                }
                if (errormessage == "") {
                    Text(text = stringResource(R.string.dis1), color = Color.LightGray)
                    Text(text = stringResource(R.string.dis2), color = Color.LightGray)
                } else {
                    Displaysnack(message = "Ultima consulta realizada: ${viewModel.lastconsume}")
                }
            }
    }
}

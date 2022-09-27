package com.example.readbitso.composeViews

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.readbitso.BitsoViewmodel
import com.example.readbitso.R
import com.example.readbitso.composeItems.MoneyCard

@Composable
fun Mainview(viewModel: BitsoViewmodel, navHostController: NavHostController)
{
    with(viewModel) {
        viewModel.selectPage("first")


        if (!isloading )
            when(errormessage)
            {
                ""-> Loading(stringResource(R.string.conecting))
                else-> Loading(stringResource(R.string.lastconsume))
            }
        else {
            Column {
                TopAppBar(
                    title = {
                        Text(text = stringResource(R.string.criptoinfo),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f))
                    }
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.90F),
                    contentPadding = PaddingValues(16.dp),
                    state = rememberLazyListState()
                ) {
                    itemsIndexed(detailedPayload) { _, list ->
                        MoneyCard(this@with, list, navHostController)
                    }
                }
                if(errormessage == "") {//Disclaimer
                    Text(text = stringResource(R.string.dis1),
                        color = Color.LightGray)
                    Text(text = stringResource(R.string.dis2),
                        color = Color.LightGray)
                }
                if(errormessage !="")
                    Displaysnack(message = stringResource(R.string.checkconection))
            }
        }
    }
}
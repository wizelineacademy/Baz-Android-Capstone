package com.example.capproject.composeviews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.capproject.viewmodels.BitsoViewModel
import com.example.capproject.items.Generalitem2
import com.example.capproject.models.Book.Payload
import com.example.capproject.support.loggerD

@Composable
fun Mainview(viewModel: BitsoViewModel, navHostController: NavHostController) {
    with(viewModel) {
        saveState("true")

        var listanueva = mutableListOf<Payload>()
        val newlist2: List<Payload> = openedPayloads

        //depuramos la lista a solo valores en peso mexicano
        newlist2.let { it ->
            listanueva = it.filter {
                it.book.contains("mxn")
            } as MutableList<Payload>
        }

        if (!isloading) {
            Loading()

        } else {
            Column {
                // val listState = rememberLazyListState()
                TopAppBar(
                    title = {
                        Text(text = "Información de Criptomonedas",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f))
                    }
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.93F),
                    contentPadding = PaddingValues(16.dp),
                    state = rememberLazyListState()
                ) {
                    itemsIndexed(listanueva) { _, list ->
                        Generalitem2(this@with, list, navHostController)
                    }
                }
                //Disclaimer
                Text(text = " * Precios expresados en Moneda Nacional", color = Color.LightGray)
                Text(text = "   solo son de carácter informativo", color = Color.LightGray)
                //
            }
        }
    }
}
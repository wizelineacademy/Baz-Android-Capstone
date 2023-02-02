package com.example.baz_android_capstone.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.baz_android_capstone.data.dataOrException.DataOrException
import com.example.baz_android_capstone.data.models.orderBook.OrderBook
import com.example.baz_android_capstone.data.models.ticker.Ticker
import com.example.baz_android_capstone.presentation.navigation.DATA_ARGUMENT_KEY
import com.example.baz_android_capstone.presentation.viewmodels.BookViewModel

@Composable
fun Description(
    navController: NavController,
    viewModel: BookViewModel
) {
    val bookName = navController.currentBackStackEntry?.arguments?.getString(DATA_ARGUMENT_KEY) ?: ""

    val ticker = produceState<DataOrException<Ticker, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = bookName.let { viewModel.getTicker(it) }
    }.value

    val order = produceState<DataOrException<OrderBook, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = bookName.let { viewModel.getOrder(it) }
    }.value

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = bookName,
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(16.dp))
        ticker.data?.payload?.let {
            Text(
                text = it.high,
                style = MaterialTheme.typography.h6
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = order.data?.payload?.asks.toString(),
            style = MaterialTheme.typography.h6
        )
    }
}

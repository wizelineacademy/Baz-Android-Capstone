package com.example.bazandroidcourse.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andcourse.domain.model.BookDetailModel
import com.andcourse.domain.model.BookOrderResumeModel
import com.example.bazandroidcourse.R
import com.example.bazandroidcourse.components.DetailNavigationBar
import com.example.bazandroidcourse.components.OperationItem
import com.example.bazandroidcourse.components.PriceItem
import com.example.bazandroidcourse.components.Subtitle
import com.example.bazandroidcourse.viewmodel.BooksViewModel

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: BooksViewModel,
    bookId: String
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = { DetailTopBar(viewModel, navController) }
        ) {
            DetailBody(
                it,
                model = viewModel.currentBookDetail.value,
                currency = viewModel.currentCurrency.value.id,
                bids = viewModel.currentBookOrders.value?.bids ?: emptyList(),
                asks = viewModel.currentBookOrders.value?.asks ?: emptyList()
            )
        }

    }
}

@Composable
private fun DetailBody(
    it: PaddingValues,
    model: BookDetailModel,
    currency: String,
    bids: List<BookOrderResumeModel>,
    asks: List<BookOrderResumeModel>
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            DetailPrices(model, currency)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MovementsList(stringResource(id = R.string.detail_binds), bids)
                MovementsList(stringResource(id = R.string.detail_asks), asks)
            }
        }
    }
}

@Composable
fun MovementsList(title: String = "", elements: List<BookOrderResumeModel>) {
    Column(modifier = Modifier) {
        Subtitle(title)
        LazyColumn() {
            items(elements) {
                OperationItem(it.price, it.amount)
            }
        }
    }
}

@Composable
private fun DetailPrices(
    model: BookDetailModel,
    currency: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        PriceItem(
            title = stringResource(id = R.string.detail_last),
            price = model.last,
            currency = currency,
            color = MaterialTheme.typography.body1.color,
        )
        PriceItem(
            title = stringResource(id = R.string.detail_max_price),
            price = model.high,
            currency = currency,
            color = colorResource(id = R.color.green),
        )
        PriceItem(
            title = stringResource(id = R.string.detail_min_price),
            price = model.low,
            currency = currency,
            color = colorResource(R.color.red)
        )

    }
}

@Composable
private fun DetailTopBar(
    viewModel: BooksViewModel,
    navController: NavController
) {
    DetailNavigationBar(
        title = viewModel.currentBook.value.name,
        model = viewModel.currentBookDetail.value,
        isDetailNavBar = true
    ) {
        navController.popBackStack()
    }
}
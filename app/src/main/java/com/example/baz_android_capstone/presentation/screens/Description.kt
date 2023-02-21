package com.example.baz_android_capstone.presentation.screens // ktlint-disable package-name

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.bumptech.glide.request.RequestOptions
import com.example.baz_android_capstone.R
import com.example.baz_android_capstone.components.genericCard.GenericCardDescription
import com.example.baz_android_capstone.components.genericCard.GenericCardList
import com.example.baz_android_capstone.components.radioButton.RadioButtonGroup
import com.example.baz_android_capstone.data.models.orderBook.OrderBook
import com.example.baz_android_capstone.data.models.ticker.Ticker
import com.example.baz_android_capstone.presentation.navigation.DATA_ARGUMENT_KEY
import com.example.baz_android_capstone.presentation.viewmodels.BookViewModel
import com.example.baz_android_capstone.util.* // ktlint-disable no-wildcard-imports
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun Description(
    navController: NavController,
    viewModel: BookViewModel
) {
    val bookName = navController.currentBackStackEntry?.arguments?.getString(DATA_ARGUMENT_KEY) ?: "crypto_currency"
    val bookNameViewModel = viewModel.bookName.value
    Log.d("bookName", "book Name: $bookName\nbook Name View Model: $bookNameViewModel")
    Log.d("order", "Order: ${viewModel.getOrders}")
    // val orderBook = viewModel.getOrders.collectAsState(initial = null).value
    // val ticker = viewModel.getTicker.collectAsState(initial = null).value
    // val ticker = viewModel.getTicker.collectAsState(initial = null).value

    /*DescriptionContent(
        bookName = bookName2,
        ticker = ticker,
        order = orderBook
    )*/
}

@Composable
fun DescriptionContent(
    bookName: String,
    ticker: Resource<Ticker>?,
    order: Resource<OrderBook>?
) {
    val askOrBids = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacer16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(spacer24))
        GlideImage(
            modifier = Modifier
                .cradle()
                .clip(shape = CircleShape)
                .size(imageSize)
                .background(Color.LightGray),
            imageModel = stringResource(
                id = R.string.url,
                bookName.substring(startIndex = 0, endIndex = bookName.length - 4)
            ),
            contentScale = ContentScale.Crop,
            placeHolder = painterResource(id = R.drawable.logo),
            error = painterResource(id = R.drawable.logo),
            previewPlaceholder = R.drawable.logo,
            requestOptions = { RequestOptions.circleCropTransform() }
        )
        Spacer(modifier = Modifier.height(spacer40))
        if (ticker is Resource.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(spacer32),
                color = GoldColor,
                strokeWidth = borderStroke4
            )
        } else {
            if (ticker != null) {
                Text(
                    text = stringResource(
                        id = R.string.Last,
                        ticker.data?.payload?.last ?: stringResource(R.string.NotAvailable),
                        bookName.substring(startIndex = bookName.length - 3, endIndex = bookName.length)
                    ),
                    style = MaterialTheme.typography.body1,
                    color = BronzeColor,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(spacer16))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (ticker != null) {
                    Text(
                        text = stringResource(
                            id = R.string.Low,
                            ticker.data?.payload?.low ?: stringResource(R.string.NotAvailable),
                            bookName.substring(startIndex = bookName.length - 3, endIndex = bookName.length)
                        ),
                        style = MaterialTheme.typography.body1,
                        color = BronzeColor,
                        fontWeight = FontWeight.Bold
                    )
                }
                if (ticker != null) {
                    Text(
                        text = stringResource(
                            id = R.string.High,
                            ticker.data?.payload?.high ?: stringResource(R.string.NotAvailable),
                            bookName.substring(startIndex = bookName.length - 3, endIndex = bookName.length)
                        ),
                        style = MaterialTheme.typography.body1,
                        color = BronzeColor,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(spacer32))
        if (ticker is Resource.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(spacer32),
                color = GoldColor,
                strokeWidth = borderStroke4
            )
        } else {
            RadioButtonGroup(options = listOf(stringResource(id = R.string.Asks), stringResource(id = R.string.Bids))) {
                askOrBids.value = it
            }
            Spacer(modifier = Modifier.height(spacer32))
            when (askOrBids.value) {
                stringResource(id = R.string.Asks) -> {
                    val listOfAsks = mutableListOf<Pair<String, String>>()
                    if (order != null) {
                        order.data?.payload?.asks?.forEach {
                            listOfAsks.add(Pair(it.price, it.amount))
                        }
                    }
                    GenericCardList(
                        elements = listOfAsks.map {
                            GenericCardDescription(
                                border = GoldColor,
                                background = PlateColor,
                                title = stringResource(R.string.PriceAmount, it.first, it.second)
                            )
                        }
                    )
                }
                stringResource(id = R.string.Bids) -> {
                    val listOfBids = mutableListOf<Pair<String, String>>()
                    if (order != null) {
                        order.data?.payload?.bids?.forEach {
                            listOfBids.add(Pair(it.price, it.amount))
                        }
                    }
                    GenericCardList(
                        elements = listOfBids.map {
                            GenericCardDescription(
                                border = GoldColor,
                                background = PlateColor,
                                title = stringResource(R.string.PriceAmount, it.first, it.second)
                            )
                        }
                    )
                }
            }
        }
    }
}

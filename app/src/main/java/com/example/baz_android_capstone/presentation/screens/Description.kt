package com.example.baz_android_capstone.presentation.screens // ktlint-disable package-name

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
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
import com.example.baz_android_capstone.data.dataOrException.DataOrException
import com.example.baz_android_capstone.data.models.orderBook.OrderBook
import com.example.baz_android_capstone.data.models.ticker.Ticker
import com.example.baz_android_capstone.presentation.navigation.DATA_ARGUMENT_KEY
import com.example.baz_android_capstone.presentation.viewmodels.BookViewModel
import com.example.baz_android_capstone.util.*
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun Description(
    navController: NavController,
    viewModel: BookViewModel
) {
    val bookName = navController.currentBackStackEntry?.arguments?.getString(DATA_ARGUMENT_KEY) ?: "crypto_currency"

    val ticker = produceState(
        initialValue = DataOrException(loading = true)
    ) {
        value = bookName.let { viewModel.getTicker(it) }
    }.value

    val order = produceState(
        initialValue = DataOrException(loading = true)
    ) {
        value = bookName.let { viewModel.getOrder(it) }
    }.value

    DescriptionContent(
        bookName = bookName,
        ticker = ticker,
        order = order
    )
}

@Composable
fun DescriptionContent(
    bookName: String,
    ticker: DataOrException<Ticker, Boolean, Exception>,
    order: DataOrException<OrderBook, Boolean, Exception>
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
        if (ticker.loading == true) {
            CircularProgressIndicator(
                modifier = Modifier.size(spacer32),
                color = GoldColor,
                strokeWidth = borderStroke4
            )
        } else {
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
            Spacer(modifier = Modifier.height(spacer16))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
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
        Spacer(modifier = Modifier.height(spacer32))
        if (ticker.loading == true && order.loading == true) {
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
                    order.data?.payload?.asks?.forEach {
                        listOfAsks.add(Pair(it.price, it.amount))
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
                    order.data?.payload?.bids?.forEach {
                        listOfBids.add(Pair(it.price, it.amount))
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

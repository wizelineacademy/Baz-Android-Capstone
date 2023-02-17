package com.example.baz_android_capstone.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.baz_android_capstone.R
import com.example.baz_android_capstone.components.dropdownMenu.DropDownTextMenu
import com.example.baz_android_capstone.components.genericCard.GenericCardInterface
import com.example.baz_android_capstone.components.genericCard.GenericCardList
import com.example.baz_android_capstone.components.genericCard.GenericCardPresentation
import com.example.baz_android_capstone.components.glideOptimized.GlideModel
import com.example.baz_android_capstone.data.models.roomModel.BookDetails
import com.example.baz_android_capstone.presentation.navigation.Screen
import com.example.baz_android_capstone.presentation.viewmodels.BookViewModel
import com.example.baz_android_capstone.util.*

@Composable
fun Principal(
    navController: NavController,
    viewModel: BookViewModel
) {
    /*
    val book = produceState<DataOrException<Book, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = viewModel.getAllBooks()
    }.value

    val listOfElements = mutableListOf<GenericCardInterface>()
    val listOfMarkets = mutableSetOf(stringResource(id = R.string.all_markets))

    book.data?.payload?.forEach {
        listOfElements.add(
            GenericCardPresentation(
                background = PaleGoldColor,
                title = it.book,
                glideModel = GlideModel(
                    url = stringResource(
                        id = R.string.url,
                        it.book.substring(startIndex = 0, endIndex = it.book.length - 4)
                    ),
                    isRoundedShape = true,
                    contentScale = ContentScale.Crop
                )
            ) {
                navController.navigate(Screen.Description.passArgs(it.book))
            }
        )
        listOfMarkets.add(it.book.substring(startIndex = it.book.length - 3, endIndex = it.book.length))
        Log.d("Icon", "https://cryptoicons.org/api/icon/${it.book.substring(startIndex = 0, endIndex = it.book.length - 4)}/200")
    }

    PrincipalContent(
        listOfMarkets = listOfMarkets.toMutableList(),
        listOfElements = listOfElements,
        book = book
    )*/
    val books = produceState<List<BookDetails>>(
        initialValue = emptyList()
    ) {
        value = viewModel.getBooks()
    }.value

    val listOfElements = mutableListOf<GenericCardInterface>()
    val listOfMarkets = mutableSetOf(stringResource(id = R.string.all_markets))

    books.forEach {
        listOfElements.add(
            GenericCardPresentation(
                background = PaleGoldColor,
                title = it.book,
                glideModel = GlideModel(
                    url = stringResource(
                        id = R.string.url,
                        it.book.substring(startIndex = 0, endIndex = it.book.length - 4)
                    ),
                    isRoundedShape = true,
                    contentScale = ContentScale.Crop
                )
            ) {
                navController.navigate(Screen.Description.passArgs(it.book))
            }
        )
        listOfMarkets.add(it.book.substring(startIndex = it.book.length - 3, endIndex = it.book.length))
    }

    PrincipalContent(
        listOfMarkets = listOfMarkets.toMutableList(),
        listOfElements = listOfElements,
        book = books
    )
}

@Composable
fun PrincipalContent(
    listOfMarkets: MutableList<String>,
    listOfElements: MutableList<GenericCardInterface>,
    // book: DataOrException<Book, Boolean, Exception>
    book: List<BookDetails>
) {
    val selectedMarket = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacer16),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(spacer40))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(imageSize)
                .presentation()
        )
        Spacer(modifier = Modifier.height(spacer56))
        DropDownTextMenu(
            listMarkets = listOfMarkets,
            label = stringResource(id = R.string.select_market)
        ) {
            selectedMarket.value = it
        }
        Spacer(modifier = Modifier.height(spacer56))
        if (book.isEmpty()) {
            CircularProgressIndicator(
                modifier = Modifier.size(spacer32),
                color = GoldColor,
                strokeWidth = borderStroke4
            )
        } else {
            GenericCardList(
                elements =
                if (selectedMarket.value == stringResource(id = R.string.all_markets)) {
                    listOfElements
                } else {
                    listOfElements.filter {
                        it.title!!.contains("_${selectedMarket.value}")
                    }
                }
            )
        }
    }
}

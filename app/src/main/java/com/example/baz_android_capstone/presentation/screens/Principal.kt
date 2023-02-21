package com.example.baz_android_capstone.presentation.screens // ktlint-disable package-name

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.* // ktlint-disable no-wildcard-imports
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
import com.example.baz_android_capstone.data.models.availableBook.Book
import com.example.baz_android_capstone.presentation.navigation.Screen
import com.example.baz_android_capstone.presentation.viewmodels.BookViewModel
import com.example.baz_android_capstone.util.* // ktlint-disable no-wildcard-imports

@Composable
fun Principal(
    navController: NavController,
    bookViewModel: BookViewModel
) {
    val books = bookViewModel.getBooks.collectAsState(initial = null)

    val listOfElements = mutableListOf<GenericCardInterface>()
    val listOfMarkets = mutableSetOf(stringResource(id = R.string.all_markets))

    books.value?.data?.payload?.forEach { payload ->
        listOfElements.add(
            GenericCardPresentation(
                background = PaleGoldColor,
                title = payload.book,
                glideModel = GlideModel(
                    url = stringResource(
                        id = R.string.url,
                        payload.book.substring(startIndex = 0, endIndex = payload.book.length - 4)
                    ),
                    isRoundedShape = true,
                    contentScale = ContentScale.Crop
                )
            ) {
                bookViewModel.bookName.value = payload.book
                navController.navigate(Screen.Description.passArgs(payload.book))
            }
        )
        listOfMarkets.add(payload.book.substring(startIndex = payload.book.length - 3, endIndex = payload.book.length))
    }

    PrincipalContent(
        listOfMarkets = listOfMarkets.toMutableList(),
        listOfElements = listOfElements,
        book = books.value
    )
}

@Composable
fun PrincipalContent(
    listOfMarkets: MutableList<String>,
    listOfElements: MutableList<GenericCardInterface>,
    book: Resource<Book>?
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
        if (book is Resource.Loading && book.data?.payload.isNullOrEmpty()) {
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

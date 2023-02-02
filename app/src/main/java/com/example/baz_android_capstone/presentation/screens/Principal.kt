package com.example.baz_android_capstone.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.baz_android_capstone.components.genericCard.GenericCardInterface
import com.example.baz_android_capstone.components.genericCard.GenericCardList
import com.example.baz_android_capstone.components.genericCard.GenericCardPresentation
import com.example.baz_android_capstone.components.glideOptimized.GlideModel
import com.example.baz_android_capstone.data.dataOrException.DataOrException
import com.example.baz_android_capstone.data.models.availableBook.Book
import com.example.baz_android_capstone.presentation.navigation.Screen
import com.example.baz_android_capstone.presentation.viewmodels.BookViewModel

@Composable
fun Principal(
    navController: NavController,
    viewModel: BookViewModel
) {
    val book = produceState<DataOrException<Book, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = viewModel.getAllBooks()
    }.value

    val listOfElements = mutableListOf<GenericCardInterface>()

    book.data?.payload?.forEach {
        listOfElements.add(
            GenericCardPresentation(
                background = Color.LightGray,
                title = it.book,
                glideModel = GlideModel(
                    url = "https://cryptoicons.org/api/icon/${it.book.substring(startIndex = 0, endIndex = it.book.length - 4)}/200",
                    isRoundedShape = true,
                    contentScale = ContentScale.Crop
                )
            ) {
                navController.navigate(Screen.Description.route)
            }
        )
        Log.d("Icon", "https://cryptoicons.org/api/icon/${it.book.substring(startIndex = 0, endIndex = it.book.length - 4)}/200")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        GenericCardList(
            elements = listOfElements
        )
    }
}

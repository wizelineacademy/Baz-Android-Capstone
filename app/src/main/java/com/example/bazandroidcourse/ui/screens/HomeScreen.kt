package com.example.bazandroidcourse.ui.compose


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.ui.components.BookItem
import com.example.bazandroidcourse.ui.components.OptionsBar
import com.example.bazandroidcourse.ui.components.Title
import com.example.bazandroidcourse.ui.viewmodel.BooksViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: BooksViewModel
) {
    Content(navController,viewModel)
}

@Composable
private fun Content(
    navController: NavController,
    viewModel: BooksViewModel
) {
    Scaffold(
        topBar = {
            TopBar()
        },
        content = {
            it
            ElementsList(viewModel.allBooks.value)
        }
    )
}

@Composable
fun ElementsList( elements:List<BookModel> = emptyList()) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items( elements){ element ->
            BookItem( element)
        }
    }
}

@Composable
private fun TopBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        Title()
        OptionsBar()
    }

}



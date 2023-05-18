package com.example.bazandroidcourse.ui.screens


import android.annotation.SuppressLint
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
import com.example.bazandroidcourse.data.model.BookModel
import com.example.bazandroidcourse.data.model.staticdata.ApplicationCurrency
import com.example.bazandroidcourse.ui.components.BookItem
import com.example.bazandroidcourse.ui.components.CurrencyDropDownMenu
import com.example.bazandroidcourse.ui.components.Title
import com.example.bazandroidcourse.ui.navigation.ApplicationScreens
import com.example.bazandroidcourse.ui.viewmodel.BooksViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: BooksViewModel
) {
    Content(navController,viewModel)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun Content(
    navController: NavController,
    viewModel: BooksViewModel
) {
    Scaffold(
        topBar = {
            TopSection(
                currentCurrency = viewModel.currentCurrency.value,
                supportedCurrencies = viewModel.supportedCurrencies
            ){
                viewModel.currentCurrency.value = it
                viewModel.getAllBooksByCurrency()
            }
        }
    ) {
        ElementsList(viewModel.allBooks.value){
            viewModel.setSelectBook(it)
            viewModel.getBookDetail(it.book)
            viewModel.getBookOrders(it.book)
            navController.navigate("${ApplicationScreens.DETAIL.name}/${it.book}")
        }
    }
}

@Composable
fun ElementsList( elements:List<BookModel> = emptyList(), onElementClick:(BookModel) -> Unit ) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items( elements){ element ->
            BookItem( element){
                onElementClick(it)
            }
        }
    }
}

@Composable
private fun TopSection(
    supportedCurrencies: List<ApplicationCurrency>,
    currentCurrency:ApplicationCurrency,
    onNewCurrencySelected: (ApplicationCurrency)->Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        Title()
        CurrencyDropDownMenu(currentCurrency,supportedCurrencies){
            onNewCurrencySelected(it)
        }
    }

}



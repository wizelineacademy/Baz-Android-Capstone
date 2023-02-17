package com.javg.cryptocurrencies.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.javg.cryptocurrencies.R
import com.javg.cryptocurrencies.data.model.CRYBook
import com.javg.cryptocurrencies.view.viewmodel.CRYHomeVM

@Composable
fun CRYHomeCryptocurrenciesScreen(){
    val homeBookVM = viewModel(modelClass = CRYHomeVM::class.java)
    val listBook by homeBookVM.listBook.observeAsState()
    homeBookVM.getBooks()

    if (listBook.isNullOrEmpty()){

    }else{
        LazyColumn(modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)){
            items(items = listBook!!){
                CRYCardInformation(it)
            }
        }
    }
}

@Composable
fun CRYCardInformation(book: CRYBook){
    Card(modifier = Modifier
        .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = 3.dp) {

        Row(Modifier.padding(16.dp)) {
            /*Image(
                painter = painterResource(id = book.image),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp))*/
            Spacer(Modifier.size(12.dp))
            Column{
                Text(text = book.book,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.size(8.dp))
                Text(text = stringResource(id = R.string.cry_maximum_price))
                Spacer(Modifier.size(8.dp))
                Text(text = stringResource(id = R.string.cry_maximum_amount))
                Spacer(Modifier.size(8.dp))
                Text(text = stringResource(id = R.string.cry_minimum_amount))
            }
        }
    }
}
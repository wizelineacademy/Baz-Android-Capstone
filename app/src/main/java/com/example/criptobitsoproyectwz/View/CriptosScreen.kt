package com.example.criptobitsoproyectwz.View

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.criptobitsoproyectwz.R
import com.example.criptobitsoproyectwz.ViewModel.ViewModelCripto
import com.example.criptobitsoproyectwz.data.model.Payload

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun CriptoScreen() {
    val criptoViewModel = viewModel(modelClass = ViewModelCripto::class.java)
    val listaCriptos by criptoViewModel.dataCripto.collectAsState()

    LazyVerticalGrid(cells = GridCells.Adaptive(150.dp)) {
        if (listaCriptos.isEmpty()) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }
        }
        items(listaCriptos) { cripto ->
            CriptoCard(cripto)

        }
    }

}

@Composable
fun CriptoCard(cripto: Payload) {
    val image = painterResource(id = R.drawable.bitso)

        Card(
            modifier = Modifier
                .wrapContentSize()
                .padding(10.dp),
            backgroundColor = Color.White,
            shape = RoundedCornerShape(8.dp),
            elevation = 16.dp
        ) {

            Column(modifier = Modifier.wrapContentSize()) {
                Image(
                    painter = image,
                    contentDescription = "icon",
                    modifier = Modifier.wrapContentSize()
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = cripto.book.uppercase(),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

            }


    }


}

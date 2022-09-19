package com.example.criptobitsoproyectwz.ui.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.criptobitsoproyectwz.R

@Composable
fun DetallesScreen(navController: NavHostController, cripto: String) {
    val image = painterResource(id = R.drawable.bitso)
    /*  val infoCriptoVM = viewModel(modelClass = ViewModelCripto::class.java)
      infoCriptoVM.getinfoCripto(cripto)

      val info by infoCriptoVM.infoCripto.collectAsState()*/

    // Log.d("Prueba", "DetallesScreen: $info")

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            backgroundColor = Color.White,
            shape = RoundedCornerShape(16.dp),
            elevation = 8.dp,
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(25.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Image(
                    painter = image,
                    contentDescription = "icon",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Text(
                    text = "book: $cripto",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Text(
                    text = "ask: $.031123",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Text(
                    text = "bid: $.031123",
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}
package com.example.criptobitsoproyectwz.ui.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.criptobitsoproyectwz.NavigationCompose.Rutas
import com.example.criptobitsoproyectwz.Util.convertir
import com.example.criptobitsoproyectwz.data.model.CriptoImage
import com.example.criptobitsoproyectwz.data.model.Criptos.Payload
import com.example.criptobitsoproyectwz.ui.ViewModel.ViewModelCripto

//@OptIn(ExperimentalFoundationApi::class)
//@Preview(showBackground = true)
@Composable
fun CriptoScreen(navController: NavHostController) {

    val criptoViewModel = viewModel(modelClass = ViewModelCripto::class.java)
    val listaCriptos by criptoViewModel.dataCripto.collectAsState()

    LazyColumn(
        modifier = Modifier.wrapContentWidth(align = Alignment.CenterHorizontally),
    ) {
        if (listaCriptos.isEmpty()) {
            item {
                Spacer(modifier = Modifier.height(350.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp, 50.dp)
                        .wrapContentSize(align = Alignment.BottomCenter)
                )
            }
            //navController.popBackStack()
        }

        items(listaCriptos) { cripto ->

            CriptoCard(cripto, navController)
        }
    }
}

@Composable
fun CriptoCard(cripto: Payload, navController: NavHostController) {
    val imageCrip = CriptoImage()
    val imagePainter = imageCrip.match(cripto = cripto.book)
    val image = painterResource(id = imagePainter)

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight().clickable {
                navController.navigate(route = "${Rutas.Detalle.ruta}/${cripto.book}")
            },
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter =  image,
                contentDescription = null,
                modifier = Modifier.size(80.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit,
            )
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = cripto.book.convertir(),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 8.dp).fillMaxWidth(),
                    color = MaterialTheme.colors.onSurface,
                )
                Text(
                    text = cripto.book.convertir(),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }




    /*Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp),
        backgroundColor = Color.White,
        shape = RoundedCornerShape(8.dp),
        elevation = 16.dp) {

        Column(modifier = Modifier.wrapContentSize()) {
            Image(
                painter = image,
                contentDescription = "icon",
                modifier = Modifier
                    .wrapContentSize()
                    .clickable {
                        navController.navigate(route = "${Rutas.Detalle.ruta}/${cripto.book}")
                    },
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = cripto.book.convertir(),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }*/
}

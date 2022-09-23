package com.example.readbitso

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.readbitso.composeViews.Displaysnack


@Composable
fun Loading( mensaje:String="" ) {
    Surface {
        TopAppBar(
            title = {
                Text(text = stringResource(R.string.criptoinfo),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth())
            }
        )

        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()) {
            // val listState = rememberLazyListState()
            CircularProgressIndicator()
        }
        if (mensaje.contains("Unknow" )){
            Displaysnack(message = "Sin conexion")
        }
    }

}
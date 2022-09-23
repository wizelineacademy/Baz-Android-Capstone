package com.example.capproject.composeviews

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope

@Preview
@Composable
fun test(){
    Displaysnack(message = "Hola soy una prueba")
}

@Composable
fun Displaysnack(message:String) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutinescope: CoroutineScope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState,)
    {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(start=16.dp, end = 16.dp),
            shape = RoundedCornerShape(25)
        ) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                LaunchedEffect(coroutinescope) {
                    val snackbarresult = scaffoldState
                        .snackbarHostState
                        .showSnackbar(
                            message = message,
                            actionLabel = "Aceptar",
                            duration = SnackbarDuration.Indefinite
                        )
                    when (snackbarresult) {
                        SnackbarResult.ActionPerformed -> {}
                        SnackbarResult.Dismissed -> {}
                    }
                }
            }
        }
    }
}

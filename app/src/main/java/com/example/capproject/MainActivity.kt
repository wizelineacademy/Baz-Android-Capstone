package com.example.capproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capproject.items.Generalitem2
import com.example.capproject.models.Book.Payload
import com.example.capproject.ui.theme.CapProjectTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity  : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContent {
                CapProjectTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background)
                    {
                        Greeting()
                    }
                }
            }
    }
}

@Composable
fun Greeting(
    viewModel:binanceViewModel = hiltViewModel()
)
{
    val a=viewModel.getCoinInfo("btc_mxn")
    println("recibi $a")

    var listanueva= mutableListOf<Payload>()

    val newlist2:List<Payload> = viewModel.openedPayloads

    //depuramos la lista a solo valores en peso mexicano
    newlist2.let {
        listanueva=it.filter {
            it.book.contains("mxn")
        } as MutableList<Payload>
    }
//

/*
    val Payloads = Observer<List<Payload>> { newName ->
        if (newName.isNotEmpty())
        {
            listanueva= newName.filter {
                it.book.contains("mxn",)
            } as MutableList<Payload>

    }
    }

    //
    viewModel.OpenedPayloads.observe(this, Payloads)
    //
*/


    Column() {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.93F),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(listanueva) {_,list->
                Generalitem2(list)
            }
        }
        //Disclaimer
       Text(text = " * Precios expresados en Moneda Nacional", color = Color.LightGray)
       Text(text = "   solo son de carácter informativo", color = Color.LightGray)
        //
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CapProjectTheme {
       Greeting()
    }
}
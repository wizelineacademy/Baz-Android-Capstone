package com.example.capproject

import android.os.Bundle
import android.util.Log
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
import androidx.lifecycle.Observer
import com.example.capproject.items.Generalitem2
import com.example.capproject.models.book.Payload
import com.example.capproject.ui.theme.CapProjectTheme

class MainActivity : ComponentActivity() {
    private var listanueva= mutableListOf<Payload>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm=binanceViewModel()

        //

            setContent {

                var salida:Boolean by remember{
                    mutableStateOf(false)
                }

                val Payloads = Observer<List<Payload>> { newName ->
                    if (newName.isNotEmpty())
                    {

                        listanueva= newName.filter {
                            it.book.contains("mxn",)
                        } as MutableList<Payload>

                        salida=true
                    }
                    else salida=false
                }

                //
                vm.OpenedPayloads.observe(this, Payloads)
                //

                vm.getbooks1()

                CapProjectTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background) {
                        if (salida)
                        Greeting(listanueva)
                    }
                }
            }
    }
}

@Composable
fun Greeting(newlist2: MutableList<Payload>) {

    Log.d("noticias 3",newlist2.toString())

    Column() {
        LazyColumn(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.93F),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(newlist2) {_,list->
                Generalitem2(list)
            }
        }
       Text(text = " * Precios expresados en Moneda Nacional", color = Color.LightGray)
        Text(text = "   solo de carácter informativo", color = Color.LightGray)
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CapProjectTheme {
//        Greeting(newlist1)
    }
}
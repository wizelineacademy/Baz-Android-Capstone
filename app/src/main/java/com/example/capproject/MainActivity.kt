package com.example.capproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.capproject.items.generalitem2
import com.example.capproject.ui.theme.CapProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CapProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {

    val vm=binanceViewModel()
    val newlist1= vm.openedPayloads

    val filteredlist=newlist1.forEach {_->
        newlist1.filter {
            it.book.contentEquals("mxn")
        }
    }
    println("listafiltrada  por pares : $filteredlist")


    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(newlist1) {_,list->
            generalitem2(list)
        }

//        generalitem(name = this.book,this.maximum_price,this.minimum_price)
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CapProjectTheme {
        Greeting()
    }
}
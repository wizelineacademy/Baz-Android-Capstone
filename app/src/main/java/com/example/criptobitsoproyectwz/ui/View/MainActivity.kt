package com.example.criptobitsoproyectwz.ui.View

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.criptobitsoproyectwz.NavigationCompose.NavigationGraph
import com.example.criptobitsoproyectwz.R
import com.example.criptobitsoproyectwz.core.CriptoResult
import com.example.criptobitsoproyectwz.data.model.Criptos.Payload
import com.example.criptobitsoproyectwz.ui.ViewModel.ViewModelCripto
import com.example.criptobitsoproyectwz.ui.theme.CriptoBitsoProyectWzTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
      val viewModelCripto : ViewModelCripto by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CriptoBitsoProyectWzTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                backgroundColor = MaterialTheme.colors.primary,
                                title = { Text(stringResource(R.string.title)) }
                            )
                        }
                    ){
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(50.dp, 50.dp)
                            )
                            Text(text = "dada")
                        }
                        var list: List<Payload>? = getlistCripto()

                        list?.let { it1 -> NavigationGraph(it1) }
                    }

                }
            }
        }
    }

    @Composable
    private fun getlistCripto(): List<Payload>? {
        var list: List<Payload>? = null
        viewModelCripto.getCriptos()
        viewModelCripto.dataCripto.observe(this) { crip ->
            when (crip) {
                is CriptoResult.Loading -> {
                    Log.d("Prueba", "onCreate: ${crip.state}")

                }
                is CriptoResult.Succes -> {
                    Log.d("Prueba", "onCreate: ${crip.data}")
                    list = crip.data.payload.filter { it.book.contains("mxn") }
                }
                is CriptoResult.Failure -> {
                    Log.d("Prueba", "onCreate: ${crip.exception}")
                }
            }
        }
        return list
    }


}


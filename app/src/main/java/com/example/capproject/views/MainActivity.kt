package com.example.capproject.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.capproject.datastore.DataStoreRepositoryImpl
import com.example.capproject.navroute.MenuNav
import com.example.capproject.support.CheckNetworkConnection
import com.example.capproject.support.loggerD
import com.example.capproject.ui.theme.CapProjectTheme
import com.example.capproject.viewmodels.NetworkViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callNetworkConnection()
        //

        setContent {
            val navController = rememberNavController()
            CapProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background)
                {
                    MenuNav(navController)
                }
            }
        }
    }


    private fun callNetworkConnection(){
        val statesRepository =DataStoreRepositoryImpl(this)
        NetworkViewModel(statesRepository).run {
            CheckNetworkConnection(application).
            observe(this@MainActivity) { isConnected ->
                if (isConnected) {
                    loggerD(message = "Conectado")
                    this.setNetworkState("Network","ok")
                } else {
                    loggerD(message = "Desconectado")
                    this.setNetworkState("Network","fail")
                }
            }
        }
    }
}




package com.baz.cmv.criptomonedas.coins.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.baz.cmv.criptomonedas.R
import com.baz.cmv.criptomonedas.coins.Coins
import com.baz.cmv.criptomonedas.coins.CoinsDetail
import com.baz.cmv.criptomonedas.coins.core.RetrofitService
import com.baz.cmv.criptomonedas.coins.data.remote.network.CoinsApiClient
import com.baz.cmv.criptomonedas.coins.data.remote.response.NetworkMoneda
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.currentCoroutineContext
import javax.inject.Inject


class CoinsViewModel (): ViewModel()  {
    val retrofit = RetrofitService.getRetrofitInstance()
    val coinsApiClient = retrofit.create(CoinsApiClient::class.java)
    val monedas = liveData {
        val resultado = coinsApiClient.obtenerMonedas().monedas.map {
            Coins(
                criptomoneda = it.book,
                precio = it.maximumPrice.toDouble()
            //imagen = obtenerImagenDeelBook(it.book)
            )
        }
        emit(resultado)
    }

    val detalle = liveData <NetworkMoneda>{
        val result = coinsApiClient.obtenerDetalle("btc_mxn")

        emit(result)
    }
}



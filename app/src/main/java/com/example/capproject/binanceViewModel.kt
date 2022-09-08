package com.example.capproject

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capproject.models.Tickers.tickets
import com.example.capproject.models.Book.Payload
import com.example.capproject.repository.BinanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class binanceViewModel @Inject constructor(private val binanceRepositoryImp: BinanceRepository) :ViewModel() {

    private var criptodivisa: List<tickets> by mutableStateOf(listOf())

    var openedPayloads: List<Payload> by mutableStateOf(listOf())
    var openedPayloadsCoin:List<com.example.capproject.models.Tickers.Payload> by mutableStateOf(listOf())

    var errorMessage: String by mutableStateOf("")


    init {
        viewModelScope.launch {
            while (true) {
                delay(2500)
                println("llamando")
                getbooks()
                delay(2500)
                println("llamado")
            }
        }
    }
    //en pruebas




    fun getCoinInfo(coin: String) {
        viewModelScope.launch {
            try {
                viewModelScope.launch {
                    openedPayloadsCoin=binanceRepositoryImp.getinfocoin(coin)
                }
            } catch (e: Exception) {
                errorMessage = e.toString()
            }

        }
    }


    ///funciona
    fun getbooks() {
        try {
            viewModelScope.launch {
                openedPayloads = binanceRepositoryImp.getbooks()
            }
        } catch (e: Exception) {
            errorMessage = e.toString()
        }
    }




}


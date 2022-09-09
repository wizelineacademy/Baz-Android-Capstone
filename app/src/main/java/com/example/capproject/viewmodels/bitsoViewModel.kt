package com.example.capproject

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capproject.models.Book.Payload
import com.example.capproject.models.trading.PayloadTrades
import com.example.capproject.repository.BitsoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BitsoViewModel @Inject constructor(private val bitsoRepositoryImp: BitsoRepository) :ViewModel() {

    var isloading: Boolean by mutableStateOf(false)
    var openedPayloads: List<Payload> by mutableStateOf(listOf())
    var openedPayloadsCoin: List<com.example.capproject.models.Tickers.Payload> by mutableStateOf(
        listOf())
    var trades: List<PayloadTrades> by mutableStateOf(listOf())
    private var read: Boolean by mutableStateOf(true)
    var update: Boolean by mutableStateOf(true)

    private var errorMessage: String by mutableStateOf("")
    var fixedposition: String by mutableStateOf("")


    init {
        viewModelScope.launch {
            while (true) {
                if (read) {
                    update=false
                    getbooks()
                    delay(2000)
                    update=true
                } else {
                   async {getCoinDetails(fixedposition)  }.run {
                       if(this.isCompleted) {
                          async { getCoinTransaction(fixedposition)
                          }.run {
                              delay(1000)
                          }
                       }
                   }
                }
            }
        }
    }

    //get details
    fun setFlagCoin(a: Boolean) {
        read = a
    }


    //set coin clickes
    fun setCoinInfo(coin: String) {
        try {
            fixedposition = coin
        } catch (e: Exception)
        {
            errorMessage = e.toString()
        }
    }
    //


    ///get books
    private fun getbooks() {
        try {
            viewModelScope.launch {
                openedPayloads = bitsoRepositoryImp.getbooks()
                isloading = openedPayloads.isNotEmpty()
            }
        } catch (e: Exception) {
            errorMessage = e.toString()
        }
    }


    //get details
    suspend fun getCoinTransaction(coin: String) {
        viewModelScope.launch {
            try {
                trades=bitsoRepositoryImp.tradesinfo(coin)
            }
            catch (e: Exception) {
                errorMessage = e.toString()
            }
        }
    }

    suspend fun getCoinDetails(coin: String) {
        viewModelScope.launch {
            try {
                    openedPayloadsCoin = bitsoRepositoryImp.getinfocoin(coin)
            } catch (e: Exception) {
                errorMessage = e.toString()
            }
        }
    }
//////
}


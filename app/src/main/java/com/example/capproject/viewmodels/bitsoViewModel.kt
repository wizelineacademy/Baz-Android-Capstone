package com.example.capproject.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capproject.datastore.DataStoreRepository
import com.example.capproject.models.Book.Payload
import com.example.capproject.models.trading.PayloadTrades
import com.example.capproject.repository.BitsoRepository
import com.example.capproject.support.loggerD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class BitsoViewModel
@Inject constructor(private val bitsoRepositoryImp: BitsoRepository,
                    private val StatesRepository: DataStoreRepository
) :ViewModel() {
    //datastore
    private var coin = "name"
    private var state = "state"
    //keys

    //Empty
    var isloading: Boolean by mutableStateOf(false)
    //List

    //observables
    var openedPayloads: List<Payload> by mutableStateOf(listOf())
    var openedPayloadsCoin: List<com.example.capproject.models.Tickers.Payload> by mutableStateOf(listOf())
    var trades: List<PayloadTrades> by mutableStateOf(listOf())
    var update: Boolean by mutableStateOf(true)
    //states

    //Flow control

    private var errorMessage: String by mutableStateOf("")
    private var errorCode: String by mutableStateOf("")
    private var fixedposition: String by mutableStateOf("")

    //viewmodel

    init {

        saveState("true")
        viewModelScope.launch{
            while (true) {
                if (getState()=="true")
                {
//                    isloading=true
                    update = false
                    getbooks()
                    delay(5000)
                    update = true
                }
                else
                {
                    getCoin()?.let {
                        getCoinDetails(it)
                        getCoinTransaction(it)
                        delay(1000)
                    }
                }
            }
        }
    }

    //set coin clickes
    fun setCoinInfo(coin: String) {
        try {
            setCoin(coin)
            fixedposition = getCoin().toString()
        } catch (e: Exception)
        {
            errorMessage = e.toString()
        }
    }

    ///get books
    private fun getbooks() {
        viewModelScope.launch {
            val a=bitsoRepositoryImp.getresponse()
            try {
                openedPayloads = bitsoRepositoryImp.getbooks()
            }catch (e:Exception){
                loggerD(message = e.message.toString())
                if (e.message?.contains("HTTP") == true)
                {
                    loggerD(message = "Error de red ")
                    errorMessage=a.error.message
                    errorCode=a.error.code.toString()
                }
            }
            isloading=openedPayloads.isNotEmpty()
        }
    }


    //get details
    private suspend fun getCoinTransaction(coin: String) {
        viewModelScope.launch {
            try {
                trades=bitsoRepositoryImp.tradesinfo(coin)
            }
            catch (e: Exception) {
                errorMessage = e.toString()
            }
        }
    }

    private suspend fun getCoinDetails(coin: String) {
        viewModelScope.launch {
            try {
                openedPayloadsCoin = bitsoRepositoryImp.getinfocoin(coin)
            } catch (e: Exception) {
                errorMessage = e.toString()
            }
        }
    }

    private fun setCoin(value: String) {
        viewModelScope.launch {
            StatesRepository.selectCoin(coin ,value)
        }
    }

    fun getCoin(): String? = runBlocking {
        StatesRepository.getCoin(coin)
    }


    fun saveState(value: String) {
        viewModelScope.launch {
            StatesRepository.putFlag(state, value)
        }
    }

    private fun getState(): String? = runBlocking {
        StatesRepository.getFlag(state)
    }
}




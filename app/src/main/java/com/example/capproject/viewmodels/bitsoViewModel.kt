package com.example.capproject.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capproject.datastore.DataStoreRepository
import com.example.capproject.models.Book.Books
import com.example.capproject.models.Book.Payload
import com.example.capproject.models.Book.detailedPayload
import com.example.capproject.models.trading.PayloadTrades
import com.example.capproject.repository.BitsoRepository
import com.example.capproject.support.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
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
    private var openedPayloads: List<Payload> by mutableStateOf(listOf())
    var detailedPayload: List<detailedPayload> by mutableStateOf(listOf())
    var openedPayloadsCoin: List<com.example.capproject.models.Tickers.Payload> by mutableStateOf(listOf())
    var trades: List<PayloadTrades> by mutableStateOf(listOf())
    var update: Boolean by mutableStateOf(true)


    //states


    //Flow control
    private var errorMessage: String by mutableStateOf("")
    private var fixedposition: String by mutableStateOf("")

    //viewmodel

    init {
        saveState("true")

        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                if (getState() == "true") {
                    update = false
                    getbooksflow()
                    delay(5000)

                    if (openedPayloads.isNotEmpty())
                    savedata()

                    update = true
                } else {
                    getCoin()?.let {
                        getCoinDetails(it)
                        getCoinTransactionFlow(it)

                        if(trades.isNotEmpty())
                        savetrades()

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
        } catch (e: Exception) {
            errorMessage = e.toString()
        }
    }


    private fun getbooksflow() {

        viewModelScope.launch(Dispatchers.IO)
        {
            books()
                .catch {
                    loggerD(message = this.toString())
                }
                .collect{ it ->
                    openedPayloads=it.payload.filter {
                        it.book.contains("mxn")
                    }
                }
            detailedPayload=GetnewList(openedPayloads)
            isloading = openedPayloads.isNotEmpty()
        }
    }

    private fun books():Flow<Books> =
        flow {
            val a=bitsoRepositoryImp.getbooks1()
            emit(a)
            if (!a.success)
            {
                if (a.error.code==404)
                    throw Exception("No encontrado/Red no disponible")
                if (a.error.code==400) throw Exception("Acceso no Permitido")
            }
        }





    private suspend fun getCoinTransactionFlow(coin: String) {
        val listamutable = mutableListOf<PayloadTrades>()
        viewModelScope.launch(Dispatchers.IO) {
            transactions(coin)
                .take(15)
                .collect{
                    listamutable.add(
                        PayloadTrades(amount= it.amount
                            ,price=it.price.take(10)
                            ,maker_side= operation(it.maker_side)
                                , book = it.book
                        )
                    )
                }
            trades=listamutable
        }
    }

    private suspend fun transactions(coin: String): Flow<PayloadTrades> =
        flow {
            bitsoRepositoryImp.tradesinfo2(coin).payload.forEach {
                emit(it)
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

    fun savedata()
    {
        viewModelScope.launch(Dispatchers.IO) {
            bitsoRepositoryImp.insert(openedPayloads)
        }
    }

    fun savetrades()
    {
        viewModelScope.launch(Dispatchers.IO) {
            bitsoRepositoryImp.inserttrades(trades)
        }
    }


}





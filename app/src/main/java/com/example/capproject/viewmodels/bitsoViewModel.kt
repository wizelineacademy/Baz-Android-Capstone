package com.example.capproject.viewmodels

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capproject.datastore.DataStoreRepository
import com.example.capproject.models.Book.Books
import com.example.capproject.models.Book.Payload
import com.example.capproject.models.Book.detailedPayload
import com.example.capproject.models.trading.PayloadTrades
import com.example.capproject.repository.BitsoRepository
import com.example.capproject.repository.BitsoRepositoryImp
import com.example.capproject.room.Currencies
import com.example.capproject.room.Operationstrades
import com.example.capproject.support.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
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
    private var net = "Network"
    //keys

    //Empty
    var isloading: Boolean by mutableStateOf(false)
    //List

    //Last
    var lastmessage:String by mutableStateOf("")
    var lastCoin:String by mutableStateOf("")
    //Consume

    //observables
    private var openedPayloads: List<Payload> by mutableStateOf(listOf())
    var detailedPayload: List<detailedPayload> by mutableStateOf(listOf())
    var openedPayloadsCoin: List<com.example.capproject.models.Tickers.Payload> by mutableStateOf(listOf())
    var trades: List<PayloadTrades> by mutableStateOf(listOf())
    var update: Boolean by mutableStateOf(true)
    var networkstatus:Boolean by mutableStateOf(true)


    //states


    //Flow control
    private var errorMessage: String by mutableStateOf("")
    private var fixedposition: String by mutableStateOf("")

    //viewmodel

    init {
        setNetworkState(net,"ok")
        saveState("true")

        viewModelScope.launch(Dispatchers.IO) {
            getserviceresponse()

            while (true) {

                when(getNetworkStatus()) {
                    "ok" -> {
                        lastmessage= ""
                        networkstatus=true
                    }
                    "fail" ->{
                        lastmessage="Ultima consulta : "
                        networkstatus=false
                    }
                    else ->{}
                }

                if (getState() == "true") {

                    update = false
                    getbooksflow()


                    if (openedPayloads.isNotEmpty())
                    {
                        savedata()
                    }
                    if (!networkstatus && openedPayloads.isEmpty() )
                    {
                        getChannelCoins()
                        loggerD(message = "Estoy con datos de la base")
                    }
                    delay(5000)
                    update = true

                } else {
                    getCoin()?.let {
                        if (networkstatus) {
                            getCoinDetails(it)
                            ChannelTransaction1(it)
                            if (trades.isNotEmpty()) {
                                lastCoin=getCoin().toString()
                                savetrades()
                            }
                        }
                        delay(3000)

                        if (!networkstatus)
                        {
                            loggerD(message = "Estoy con datos de la base")
                            getChannelTradesInfo()
                        }

                    }
                }
            }
        }
    }

    private  fun getserviceresponse() {
        bitsoRepositoryImp.test()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.success)
                    networkstatus = true
                else
                {errorMessage=it.Error.message}
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
                .catch {}
                //  loggerD(message = this.toString())}
                .collect { it ->
                    openedPayloads = it.payload.filter {
                        it.book.contains("mxn")
                    }
                }
            detailedPayload = GetnewList(openedPayloads)
            isloading = openedPayloads.isNotEmpty()
        }
    }

    private fun books(): Flow<Books> =
        flow {
            val a = bitsoRepositoryImp.getbooks1()
            emit(a)
            if (!a.success) {
                if (a.error.code == 404)
                    throw Exception("No encontrado/Red no disponible")
                if (a.error.code == 400) throw Exception("Acceso no Permitido")
            }
        }

    private suspend fun ChannelTransaction1(coin: String) {
        val listamutable = mutableListOf<PayloadTrades>()
        val chanel = chaneltransaction(coin)
        chanel.consumeAsFlow()
            .take(15)
            .collect {
                listamutable.add(
                    PayloadTrades(amount = it.amount.take(10),
                        price = it.price.take(10),
                        maker_side = operation(it.maker_side),
                        book = it.book
                    )
                )
            }
        chanel.cancel()
        trades = listamutable
    }

    private fun chaneltransaction(coin: String): ReceiveChannel<PayloadTrades> =
        viewModelScope.produce {
            bitsoRepositoryImp.tradesinfo2(coin).payload.forEachIndexed {index,it->
                send(it)
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

    fun savedata() {
        viewModelScope.launch(Dispatchers.IO) {
            bitsoRepositoryImp.insert(openedPayloads.toSet())
        }
    }

    fun savetrades() {
        viewModelScope.launch(Dispatchers.IO) {
            bitsoRepositoryImp.inserttrades(trades)
        }
    }

    private suspend fun getNetworkStatus():String? =
        StatesRepository.getNetworkStatus(net)

    private fun setNetworkState(key1:String, key2:String){
        viewModelScope.launch{
            StatesRepository.setNetworkStatus(key1,key2)
        }
    }

    private suspend fun getCoinsInfo():ReceiveChannel<Currencies> =
        viewModelScope.produce (Dispatchers.IO){
            bitsoRepositoryImp.getCurrencies().forEach {
                send(it)
            }
        }

    private suspend fun getChannelCoins()
    {
        val channel=getCoinsInfo()
        val listamutable = mutableListOf<Payload>()

        channel.consumeAsFlow()
            .collect{
                listamutable.add(
                    Payload(
                        book = it.Name.toString(),
                        maximum_price = it.maxvalue.toString(),
                        minimum_price = it.minvalue.toString()
                    )
                )
            }
        openedPayloads=listamutable
        channel.cancel()
    }

    private fun getTradesInfo1():ReceiveChannel<Operationstrades> =
        viewModelScope.produce<Operationstrades> (Dispatchers.IO) {
            bitsoRepositoryImp.getTrades1().forEach {
                send(it)
            }
        }

    private suspend fun getChannelTradesInfo(): MutableList<PayloadTrades> {
        val listamutable = mutableListOf<PayloadTrades>()
        val channel=getTradesInfo1()
        channel.consumeAsFlow()
            .take(15)
            .collect{
                listamutable.add(PayloadTrades(
                    amount = it.Amount.toString(),
                    book = it.pair.toString(),
                    maker_side = it.Type.toString(),
                    price = it.Price.toString()
                ))
            }
        channel.cancel()
        trades=listamutable

        return listamutable
    }
    //closing viewmodel
}




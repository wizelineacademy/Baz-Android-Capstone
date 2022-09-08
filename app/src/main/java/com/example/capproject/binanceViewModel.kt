package com.example.capproject

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capproject.interfaces.ApiBinance
import com.example.capproject.models.book.Books
import com.example.capproject.models.book.Payload
import com.example.capproject.models.OrderBooks.Broakerbook
import com.example.capproject.models.Tickers.tickets
import com.example.capproject.repository.BinanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class binanceViewModel @Inject constructor(private val binanceRepositoryImp: BinanceRepository) :ViewModel() {

    private var criptodivisa: List<tickets> by mutableStateOf(listOf())
    private var orderinfo: List<Broakerbook> by mutableStateOf(listOf())
    var openedBooks: List<Books> by mutableStateOf(listOf())
    var openedPayloads: List<Payload> by mutableStateOf(listOf())


    private var _OpenedBooks = MutableLiveData<List<Books>>(listOf())
    var OpenedBooks: LiveData<List<Books>> = _OpenedBooks

    private var _OpenedPayloads = MutableLiveData<List<Payload>>(listOf())
    var OpenedPayloads: LiveData<List<Payload>> = _OpenedPayloads




    var errorMessage: String by mutableStateOf("")


    init{
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


    fun getOrdersInfo(coin: String) {
        val apiService = ApiBinance.getInstance()
        viewModelScope.async {
            val criptodivisa = apiService.specificBook(coin)
            orderinfo = listOf(criptodivisa)
        }
    }

    fun getCoinInfo(coin: String) {
        viewModelScope.async {
            casescripto.coinInfo.coinInfo(object : coinNews {
                override fun cripoInformation(ticket: com.example.capproject.models.Tickers.Payload) {
                    println("ticket: $ticket")
            //        _OpenedPayloads.value = listOf(ticket)
                }
            }, coin)
            delay(2000)
        }

    }


    ///funciona
    fun getbooks() {
        try {
            viewModelScope.launch {
               openedPayloads=binanceRepositoryImp.getbooks()
            }
        } catch (e: Exception) {
            errorMessage = e.toString()
        }
    }


///esquema completo

    fun setbooks(lista: List<Books>, filter: List<Payload>) {
        openedBooks = lista
        _OpenedBooks.value = openedBooks
        _OpenedPayloads.value=filter
        openedPayloads=filter
    }

     fun getbooks1() {
        viewModelScope.launch {
            try {
                casescripto.Books1.openedbooks(object : criptoNews {
                    override fun openedbooks(lista: List<Books>, filter: List<Payload>) {
                        setbooks(lista,filter)
                    }
                })
            } catch (e: Exception) {
                errorMessage = e.toString()
            }
        }

    }
}

sealed class casescripto {
    object Books1:casescripto()
    {
        suspend fun openedbooks(callback:criptoNews){
            val apiService = ApiBinance.getInstance()
            val lista=apiService.getBooks()
            val filter =lista.payload
                 callback.openedbooks(listOf(lista),filter)
        }
    }
    object coinInfo:casescripto()
    {
        suspend fun coinInfo(callback: coinNews,coin: String)
        {
            val apiService = ApiBinance.getInstance()
            val criptodivisa = apiService.specificTicker(coin).payload
            callback.cripoInformation(criptodivisa)
        }
        //listOf(books)
    }

}




interface criptoNews{
    fun openedbooks(lista: List<Books>, filter: List<Payload>)

}
interface coinNews{
    fun cripoInformation(ticket:com.example.capproject.models.Tickers.Payload)
}
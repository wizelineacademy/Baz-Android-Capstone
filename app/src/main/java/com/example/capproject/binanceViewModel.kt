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
import kotlinx.coroutines.*


class binanceViewModel() :ViewModel()
{

    private var criptodivisa: List<tickets> by mutableStateOf(listOf())
    private var orderinfo: List<Broakerbook> by mutableStateOf(listOf())
    var openedBooks: List<Books> by mutableStateOf(listOf())
    var openedPayloads: List<Payload> by mutableStateOf(listOf())


    private var _OpenedBooks= MutableLiveData<List<Books>>(listOf())
     var OpenedBooks:LiveData<List<Books>> = _OpenedBooks


    var errorMessage: String by mutableStateOf("")


    init {
        viewModelScope.launch {
           while (true) {

                //  newsRepository.fetchOpenedBooks()
                // Intermediate catch operator. If an exception is thrown,
                // catch and update the UI
//                .catch { exception -> notifyError(exception) }
//                .collect { favoriteNews ->

              delay(5000)
                getbooks1()
//                getOrdersInfo("btc_mxn")
  //              getCoinInfo("btc_mxn")

//                Log.d("mensaje nuevo", CriptoInformation.get().payload.toString())

    //            Log.d(" cripto divisa Recibi ahora :", criptodivisa.toString())
      //          Log.d(" cripto ordenes divisa  :", orderinfo.toString())

                _OpenedBooks.value=openedBooks
//                Log.d(" cripto  space  :", "------------------")

                //               val json = json.parseToJsonElement(criptodivisa)
                //                val map = json.jsonObject.toMap()

//                Log.d("mensaje nuevo filtrado", CriptoInformation2.filter {
  //                  it.
    //            })
//            val     (criptodivisa) = nuevovalor
//                   openedBooks[0].payload[0].fees.structure[0].volume
            }
          }
    }

    //en pruebas

    fun inicio(){}

    fun getOrdersInfo(coin: String) {
        val apiService = ApiBinance.getInstance()
        viewModelScope.async {
            val criptodivisa = apiService.specificBook(coin)
                orderinfo= listOf(criptodivisa)
        }
    }

    fun getCoinInfo(coin: String) {
        val apiService = ApiBinance.getInstance()

        viewModelScope.launch {

             criptodivisa = listOf(apiService.specificTicker(coin))
        }
    }


    ///funciona
    fun getbooks() {
        val apiService = ApiBinance.getInstance()
        try {
            viewModelScope.launch  {
                val criptodivisa = apiService.getBooks()
                openedBooks = listOf(criptodivisa)
                println("aqui : $openedBooks")
            }
        } catch (e: Exception) {
            errorMessage = e.toString()
        }
    }


///esquema completo

    fun setbooks(lista: List<Books>){
        openedBooks= lista
    }

    fun getbooks1() {
        try {
            viewModelScope.launch {
//                param.printmessage(this.toString())
                    casescripto.Books1.openedbooks(object:criptoNews{
                        override fun openedbooks(lista: List<Books>, listafilter: List<Payload>) {
                            setbooks(lista)
                            openedPayloads=listafilter

                        //                            super.openedbooks(lista)
                        }
                       override fun printmessage(message: String) {
                            super.printmessage(message)
                        }

                    })
               }

        } catch (e: Exception) {
            errorMessage = e.toString()
        }
    }
//

    fun flowgetbooks()  {
    }
}


sealed class casescripto {
    object Books1:casescripto()
    {
    suspend fun openedbooks(callback:criptoNews){
        val apiService = ApiBinance.getInstance()
        val lista=apiService.getBooks()
        val listafilter=apiService.getBooks().payload
        callback.openedbooks(listOf(lista),listafilter)
    }
        //listOf(books)
    }
}

interface criptoNews{
    fun openedbooks(lista: List<Books>, listafilter: List<Payload>)
    fun printmessage(message:String)= println(message)
}
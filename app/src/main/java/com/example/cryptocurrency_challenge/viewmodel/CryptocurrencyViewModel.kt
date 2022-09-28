package com.example.cryptocurrency_challenge.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency_challenge.data.model.Available_books_response
import com.example.cryptocurrency_challenge.data.model.InfoTickerResponse
import com.example.cryptocurrency_challenge.data.model.Payload
import com.example.cryptocurrency_challenge.data.model.Payload_Ticker
import com.example.cryptocurrency_challenge.data.network.ApiBitsoClient
import com.example.cryptocurrency_challenge.data.network.ApiBitsoService
import com.example.cryptocurrency_challenge.data.network.config.StartApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalArgumentException

class CryptocurrencyViewModel: ViewModel()  {

    var availableBookModel  : MutableLiveData<List<Payload>?>  = MutableLiveData()
    var payLoadTicker       : MutableLiveData<Payload_Ticker?> = MutableLiveData()
    val isLoading           = MutableLiveData<Boolean>()

    fun getAvailable_books_rx(){

    }

    fun getAvailable_books(){
            viewModelScope.launch() {
                isLoading.postValue(true)
                val callRespuesta= StartApplication.webServiceGlobal.getAllCurrencies()
                callRespuesta.enqueue(object : Callback<Available_books_response> {
                    override fun onResponse(
                        call: Call<Available_books_response>,
                        response: Response<Available_books_response>
                    ) {
                        isLoading.postValue(false)
                        if(response.isSuccessful){
                            response.let {
                                val responseList = response.body()?.payload
                                val list_mxn_currencys  = responseList?.filter { it.book.contains("mxn") }
                                if(list_mxn_currencys!=null && list_mxn_currencys.size>0){
                                    availableBookModel.value = list_mxn_currencys
                                }
                                else{
                                    Log.i("Response", "Lista vacia")
                                }
                            }
                        }
                    }
                    override fun onFailure(call: Call<Available_books_response>, t: Throwable) {
                        isLoading.postValue(false)
                        Log.i("ERROR", "ERROR")
                    }
                })
            }
    }

    fun getInfoTicker(currency_name: String?) {
        viewModelScope.launch() {
            val callRespuesta= StartApplication.webServiceGlobal.getInfoTicker(currency_name!!)
            callRespuesta.enqueue(object : Callback<InfoTickerResponse> {
                override fun onResponse(
                    call: Call<InfoTickerResponse>,
                    response: Response<InfoTickerResponse>
                ) {
                    isLoading.postValue(false)
                    val responseTickerAux = response.body()?.payload
                    payLoadTicker.value= responseTickerAux
                }

                override fun onFailure(call: Call<InfoTickerResponse>, t: Throwable) {
                    isLoading.postValue(false)
                    Log.i("ERROR", "ERROR")
                }

            })
        }
    }

    private suspend fun testVM(){
        withContext(Dispatchers.IO){   // se cambia la corrutina a otro hilo


        }
    }

    override fun onCleared() {

        //repository.close()
    }

}

class MainViewModelFactory(private val apiBitsoClient: ApiBitsoClient) :
    ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ApiBitsoClient::class.java)
            .newInstance(apiBitsoClient)
    }
}

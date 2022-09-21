package com.brendarojas.criptomonedaswizeline.webservice

import android.util.Log
import com.brendarojas.criptomonedaswizeline.config.InitialApplication
import com.brendarojas.criptomonedaswizeline.data.BidsResponse
import com.brendarojas.criptomonedaswizeline.data.BookResponse
import com.brendarojas.criptomonedaswizeline.data.TickerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CryptoDao {

    //Consumir servicio https://bitso.com/api_info#available-books
    fun getServiceAvailableBooks(){
        var callAnswer = InitialApplication.webServiceGlobal.available_books()
        callAnswer.enqueue(object: Callback<BookResponse> {
            override fun onResponse(
                call: Call<BookResponse>,
                response: Response<BookResponse>
            ) {
                if (response.isSuccessful){
                    Log.d("mensaje", "${response.body()?.payload}")
                }else{
                    Log.d("mensaje", "false")
                }
            }
            override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                Log.d("mensaje", "error")
            }
        })
    }

    //Consumir servicio https://bitso.com/api_info#ticker
    fun getServiceByTicker(){
        var book = "btc_mxn"
        var callAnswer = InitialApplication.webServiceGlobal.getTicker(book)
        callAnswer!!.enqueue(object: Callback<TickerResponse>{
            override fun onResponse(
                call: Call<TickerResponse>,
                response: Response<TickerResponse>
            ) {
                if (response.isSuccessful){
                    Log.d("mensaje", "${response.body()?.payload}")
                }else{
                    Log.d("mensaje", "false")
                }              }

            override fun onFailure(call: Call<TickerResponse>, t: Throwable) {
                Log.d("mensaje", "error")
            }
        })
    }

    //Consumir servicio https://api.bitso.com/v3/order_book/?book=btc_mxn
    fun getServiceOrderBook() {
        val callAnswer = InitialApplication.webServiceGlobal.getOrderBook("btc_mxn")
        callAnswer.enqueue(object : Callback<BidsResponse> {
            override fun onResponse(call: Call<BidsResponse>, response: Response<BidsResponse>) {
                if (response.isSuccessful){
                    Log.d("mensaje", "${response.body()?.payload}")
                }else{
                    Log.d("mensaje", "false")
                }            }

            override fun onFailure(call: Call<BidsResponse>, t: Throwable) {
                Log.d("mensaje", "error")
            }


        })
    }

}
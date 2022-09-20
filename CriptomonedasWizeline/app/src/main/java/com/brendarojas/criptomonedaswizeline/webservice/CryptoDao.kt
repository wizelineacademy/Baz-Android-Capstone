package com.brendarojas.criptomonedaswizeline.webservice

import android.util.Log
import com.brendarojas.criptomonedaswizeline.config.InitialApplication
import com.brendarojas.criptomonedaswizeline.models.AvailableBooks
import com.brendarojas.criptomonedaswizeline.models.Book
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CryptoDao {

    fun consumirServicioGet(){
        var callRespuesta = InitialApplication.webServiceAvailableBooks.available_books()
        callRespuesta.enqueue(object: Callback<AvailableBooks> {
            override fun onResponse(
                call: Call<AvailableBooks>,
                response: Response<AvailableBooks>
            ) {
                if (response.isSuccessful){
                    Log.d("mensaje", "${response.body()?.payload}")
                }
            }
            override fun onFailure(call: Call<AvailableBooks>, t: Throwable) {
                Log.d("mensaje", "error")
            }
        })
    }

}
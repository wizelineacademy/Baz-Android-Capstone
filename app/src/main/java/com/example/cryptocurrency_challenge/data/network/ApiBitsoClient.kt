package com.example.cryptocurrency_challenge.data.network

import com.example.cryptocurrency_challenge.data.model.Available_books_response
import com.example.cryptocurrency_challenge.data.network.config.RetrofitClient
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ApiBitsoClient (private val apiBitsoService: ApiBitsoService){


    fun getAvailable_books(): Single<Response<Available_books_response>> {
        return apiBitsoService.getAllCurrenciesRX()
    }

    fun getInfoTicker(currency_name: String?){



    }

    //private val retrofit = RetrofitClient.getRetrofit()

    /*suspend fun getAvailable_books_(): List<Available_books_response> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiBitsoClient::class.java).getAllQuotes()
            response.body() ?: emptyList()
        }
    }*/



}
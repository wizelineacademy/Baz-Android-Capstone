package com.example.cryptocurrency_challenge.network.api
import com.example.cryptocurrency_challenge.model.Available_books_response
import com.example.cryptocurrency_challenge.model.InfoTickerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

/*
* https://bitso.com/api_info#available-books
* https://bitso.com/api_info#ticker
* https://bitso.com/api_info#order-book
*/

interface ApiBitso {

    //@GET("available_books/")
    @GET("https://api.bitso.com/v3/available_books/")
    fun getAllCurrencies(): Call<Available_books_response>

    //@GET("available_books/")
    @POST("https://bitso.com/api_info#ticker/")
    fun getInfoTicker(): Call<InfoTickerResponse>
    //fun getInfoTicker(@Query("book") book: String): Call<InfoTickerResponse>




}
package com.example.cryptocurrency_challenge.data.network
import com.example.cryptocurrency_challenge.data.model.Available_books_response
import com.example.cryptocurrency_challenge.data.model.InfoTickerResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/*
* https://bitso.com/api_info#available-books
* https://bitso.com/api_info#ticker
* https://bitso.com/api_info#order-book
*/

interface ApiBitsoService {

    @GET("https://api.bitso.com/v3/available_books/")
    fun getAllCurrencies(): Call<Available_books_response>

    @GET("https://api.bitso.com/v3/available_books/")
    fun getAllCurrenciesRX(): Single<Response<Available_books_response>>   // Single para rxjava observable que solo espera una sola respuesta o un solo resultado

    @GET("https://api.bitso.com/v3/ticker/")
    fun getInfoTicker(@Query("book") value:String): Call<InfoTickerResponse>

    //@GET ("https://api.bitso.com/v3/order_book/")
    //fun getOrderBook(@Query("book") value:String): Call<InfoTickerResponse>

}
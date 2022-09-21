package com.brendarojas.criptomonedaswizeline.webservice

import com.brendarojas.criptomonedaswizeline.data.BidsResponse
import com.brendarojas.criptomonedaswizeline.data.BookResponse
import com.brendarojas.criptomonedaswizeline.data.TickerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoApi {

    //Obtener libros disponibles
    @GET("available_books/")
    fun available_books():Call<BookResponse>

    //Obtener info ticker
    @GET("ticker/")
    fun getTicker(
        @Query("book") book: String
    ): Call<TickerResponse>?

    //Obtener order book
    @GET("order_book/")
    fun getOrderBook( @Query("book") book: String ): Call<BidsResponse>

}
package com.example.capproject.interfaces

import com.example.capproject.models.Tickers.Ticker
import com.example.capproject.models.Tickers.tickets
import com.example.capproject.models.Book.Books
import retrofit2.http.GET
import retrofit2.http.Query

interface BinanceDataSource {
    //--------
    @GET("ticker") //get all
    suspend fun getTicker() : Ticker

    @GET("ticker/") // get especific
    suspend fun specificTicker(@Query("book") book:String ) : tickets
//----------

    //------
    @GET("available_books")
    suspend fun getBooks() : Books

    //@GET("order_book/")
   // suspend fun specificBook(@Query("book") book:String ) : Broakerbook
//--------



}
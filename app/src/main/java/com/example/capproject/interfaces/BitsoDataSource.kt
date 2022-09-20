package com.example.capproject.interfaces

import com.example.capproject.models.Tickers.Ticker
import com.example.capproject.models.Tickers.tickets
import com.example.capproject.models.Book.Books
import com.example.capproject.models.trading.PayloadTrades
import com.example.capproject.models.trading.Trades
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface BitsoDataSource {
    //--------
    @GET("ticker") //get all
     fun getTicker() : Observable<Ticker>

    @GET("ticker/") // get especific
    suspend fun specificTicker(@Query("book") book:String ) : tickets
//----------

    //------
    @GET("available_books")
    suspend fun getBooks() : Books

    @GET("trades/")
    suspend fun specificTrade(@Query("book") book:String ) :Trades

}
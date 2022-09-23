package com.example.readbitso.interfaces
import com.example.readbitso.models.bitsoBooks.Books
import com.example.readbitso.models.bitsotickers.Tickers
import com.example.readbitso.models.trading.Trades
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface BitsoDataSource {
    //--------
//    @GET("ticker") //get all
  //   fun getTicker() : Observable<ticker>

    @GET("ticker/") // get especific
    suspend fun specificTicker(@Query("book") book:String ) : Tickers
//----------

    //------
    @GET("available_books")
    fun getBooks() : Observable<Books>

    @GET("trades/")
    suspend fun specificTrade(@Query("book") book:String ) : Trades

}
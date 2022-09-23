package com.example.readbitso.interfaces
import com.example.capproject.models.Tickers.Tickets
import com.example.readbitso.models.bitsoBooks.Books
import com.example.readbitso.models.trading.Trades
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface BitsoDataSource {
    //--------
//    @GET("ticker") //get all
  //   fun getTicker() : Observable<ticker>

    @GET("ticker/") // get especific
    suspend fun specificTicker(@Query("book") book:String ) : Tickets
//----------

    //------
    @GET("available_books")
    suspend fun getBooks() : Books

    @GET("trades/")
    suspend fun specificTrade(@Query("book") book:String ) : Trades

}
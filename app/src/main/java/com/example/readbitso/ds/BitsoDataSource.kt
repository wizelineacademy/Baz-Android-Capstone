package com.example.readbitso.ds
import com.example.readbitso.models.bitsoModels.bitsoBooks.Books
import com.example.readbitso.models.bitsoModels.bitsoBooks.bitsotickers.Tickers
import com.example.readbitso.models.bitsoModels.bitsoBooks.trading.Trades
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
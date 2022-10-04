package com.example.readbitso.ds
import com.example.readbitso.models.bitsoModels.bitsoBooks.Books
import com.example.readbitso.models.bitsoModels.bitsoBooks.bitsotickers.Tickers
import com.example.readbitso.models.bitsoModels.bitsoBooks.trading.Trades
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response as Response1

interface BitsoDataSource {

    @GET("ticker/") // get especific
    suspend fun specificTicker(@Query("book") book: String): Response1<Tickers>

    @GET("available_books")
    fun getBooks(): Observable<Books>

    @GET("available_books")
    suspend fun getBooks1(): Response1<Books>


    @GET("trades/")
    suspend fun specificTrade(@Query("book") book: String): Response1<Trades>
}

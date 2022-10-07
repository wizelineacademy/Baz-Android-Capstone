package com.example.readbitso.datasource
import com.example.readbitso.models.bitsoModels.bitsoBooks.Books
import com.example.readbitso.models.bitsoModels.bitsoBooks.bitsotickers.Tickers
import com.example.readbitso.models.bitsoModels.bitsoBooks.trading.Trades
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response as Response1

interface BitsoDataSource {

    @GET("ticker/")
    suspend fun getTickerResponse(@Query("book") book: String): Response1<Tickers>

    @GET("available_books")
    fun getBooks(): Single<Books>

    @GET("available_books")
    suspend fun getBooksResponse(): Response1<Books>


    @GET("trades/")
    suspend fun getTradesResponse(@Query("book") book: String): Response1<Trades>
}

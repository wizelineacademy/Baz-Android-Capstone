
package wizeline.crypto.currency.data.resource.remote

import retrofit2.http.GET
import retrofit2.http.Query
import wizeline.crypto.currency.data.models.AvailableBooksDto
import wizeline.crypto.currency.data.models.OrderBookDto
import wizeline.crypto.currency.data.models.TickerDto

interface CryptoCurrenciesApi {

    @GET("available_books/")
    suspend fun getAvailableBooks(): AvailableBooksDto

    @GET("ticker/")
    suspend fun getInformationTrading(@Query("book")book:String): TickerDto

    @GET("order_book/")
    suspend fun getOrderBook(@Query("book")book:String): OrderBookDto



}


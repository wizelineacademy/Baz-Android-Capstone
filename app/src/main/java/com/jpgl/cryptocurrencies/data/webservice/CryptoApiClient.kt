package com.jpgl.cryptocurrencies.data.webservice

import com.jpgl.cryptocurrencies.data.model.response.BidsModelResponse
import com.jpgl.cryptocurrencies.data.model.response.BookModelResponse
import com.jpgl.cryptocurrencies.data.model.response.TickerModelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApiClient {
    //Obtener libros disponibles
    @GET("available_books/")
    suspend fun getAvailable_books(): Response<BookModelResponse>

    //Obtener info ticker
    @GET("ticker/")
    suspend fun getTicker( @Query("book") book: String ): Response<TickerModelResponse>

    //Obtener order book
    @GET("order_book/")
    suspend fun getOrderBook( @Query("book") book: String ): Response<BidsModelResponse>
}
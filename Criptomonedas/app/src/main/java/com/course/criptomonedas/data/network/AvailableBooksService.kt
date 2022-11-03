package com.course.criptomonedas.data.network

import com.course.criptomonedas.data.models.AvailableBooks
import retrofit2.http.GET
import retrofit2.http.Query

interface AvailableBooksService {

    @GET("available_books/")
    suspend fun getBooks(): AvailableBooks

    @GET("ticker/")
    fun getDetailBook(@Query("book") id: String)

    @GET("order_book/")
    fun getBooksById(@Query("book") id: String)
}
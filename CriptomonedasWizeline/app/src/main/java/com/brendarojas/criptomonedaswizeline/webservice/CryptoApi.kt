package com.brendarojas.criptomonedaswizeline.webservice

import com.brendarojas.criptomonedaswizeline.models.AvailableBooks
import com.brendarojas.criptomonedaswizeline.models.Book
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoApi {

    //Obtener libros disponibles
    @GET("available_books/")
    fun available_books():Call<AvailableBooks>

}
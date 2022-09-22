package com.example.criptobitsoproyectwz.data.network

import android.database.Observable
import com.example.criptobitsoproyectwz.data.model.Criptos.BaseResult
import com.example.criptobitsoproyectwz.data.model.OrderBook.BaseBookOrder
import com.example.criptobitsoproyectwz.data.model.Ticket.PayloadCripto
import com.example.criptobitsoproyectwz.data.model.Ticket.TicketResult
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface BitsoService {

    @GET
    suspend fun getCriptos(@Url url: String): Response<BaseResult>

    @GET
    suspend fun getTicketInformation(@Url url: String): Response<TicketResult>

    @GET
    suspend fun getBookOrder(@Url url: String): Response<BaseBookOrder>

    @GET("/available_books")
    fun getCriptosService(): Call<BaseResult>

    @GET("/available_books")
    fun getCriptosServiceRX(): Single<BaseResult>

}
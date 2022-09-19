package com.example.criptobitsoproyectwz.data.network

import com.example.criptobitsoproyectwz.data.model.Criptos.BaseResult
import com.example.criptobitsoproyectwz.data.model.Ticket.PayloadCripto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiServiceBitsoInterface {
    @GET
    suspend fun getCriptos(@Url url: String): Response<BaseResult>

    @GET
    suspend fun getTicketInformation(@Url url: String): Response<PayloadCripto>
}
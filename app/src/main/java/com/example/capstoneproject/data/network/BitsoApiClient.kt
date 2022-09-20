package com.example.capstoneproject.data.network

import com.example.capstoneproject.data.model.AvailableBooksResponse
import retrofit2.Response
import retrofit2.http.GET

interface BitsoApiClient {

    @GET("available_books/")
    suspend fun getAllAvailableBooks(): Response<AvailableBooksResponse>
}
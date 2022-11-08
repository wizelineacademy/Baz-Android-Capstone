package com.example.myapplication.data.remote

import com.example.myapplication.application.AppConstants
import com.example.myapplication.data.model.BookResponse
import com.example.myapplication.repository.WebService

class BitsoDataSource(private val webService: WebService) {

    suspend fun getAvailableBooks(): BookResponse {
        return webService.getAvailableBooks(AppConstants.API_KEY)
    }
    }
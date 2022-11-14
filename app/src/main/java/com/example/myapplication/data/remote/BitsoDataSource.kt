package com.example.myapplication.data.remote

import com.example.myapplication.application.AppConstants
import com.example.myapplication.data.model.BookResponse
import com.example.myapplication.data.model.OrderBooksModel
import com.example.myapplication.data.model.TickerPayloadResponse
import com.example.myapplication.repository.WebService

class BitsoDataSource(private val webService: WebService) {


    suspend fun getTicker(): TickerPayloadResponse {
        return webService.getTicker(AppConstants.API_KEY)
    }

    suspend fun getOrderBook(): OrderBooksModel {
        return webService.getOrderBook(AppConstants.API_KEY)
    }

    suspend fun getAvailableBook(): BookResponse {
        return  webService.getAvailableBook(AppConstants.API_KEY)
    }

}
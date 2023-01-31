package com.jpgl.cryptocurrencies.data.webservice

import com.jpgl.cryptocurrencies.data.model.response.BidsModelResponse
import com.jpgl.cryptocurrencies.data.model.response.BookModelResponse
import com.jpgl.cryptocurrencies.data.model.response.TickerModelResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CryptoService @Inject constructor(
    private val apiClient: CryptoApiClient
){

    suspend fun getAvailableBooks(): BookModelResponse {
        return withContext(Dispatchers.IO){
            val response = (apiClient).getAvailable_books()
            response.body()!!
        }
    }

    suspend fun getTicker(): TickerModelResponse{
        return withContext(Dispatchers.IO){
            val response = (apiClient).getTicker("btc_mxn")
            response.body()!!
        }
    }

    suspend fun getOrderBooks(): BidsModelResponse{
        return withContext(Dispatchers.IO){
            val response = (apiClient).getOrderBook("btc_mxn")
            response.body()!!
        }
    }

}
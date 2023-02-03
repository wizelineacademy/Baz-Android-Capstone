package com.carteagal.baz_android.data.repository

import com.carteagal.baz_android.data.dataSources.CryptoRemoteDataSources
import com.carteagal.baz_android.data.model.tickerResponse.TickerResponse
import javax.inject.Inject

class TickerRepository @Inject constructor(
    private val apiDataSource: CryptoRemoteDataSources
){
    suspend fun getTickerBook(book: String): TickerResponse{
        return apiDataSource.getTicker(book)
    }
}
package com.example.cryptocurrency_challenge.repository

import com.example.cryptocurrency_challenge.data.model.InfoTickerResponse
import com.example.cryptocurrency_challenge.data.network.NetworkDataSource
import javax.inject.Inject

class TickerRepositoryImpl @Inject constructor (private val remoteDataSource : NetworkDataSource) :
    TickerRepository {
    override suspend fun get_Ticker(currency_name: String?): InfoTickerResponse =
        remoteDataSource.getTicker(currency_name).body()!!
}



